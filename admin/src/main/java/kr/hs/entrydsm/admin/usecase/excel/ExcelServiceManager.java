package kr.hs.entrydsm.admin.usecase.excel;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import kr.hs.entrydsm.admin.entity.schedule.Schedule;
import kr.hs.entrydsm.admin.entity.schedule.ScheduleRepository;
import kr.hs.entrydsm.admin.entity.schedule.Type;
import kr.hs.entrydsm.admin.integrate.applicaton.ApplicationRepository;
import kr.hs.entrydsm.admin.integrate.score.ScoreRepository;
import kr.hs.entrydsm.admin.usecase.dto.applicant.ApplicantInfo;
import kr.hs.entrydsm.admin.usecase.dto.applicant.UserInfo;
import kr.hs.entrydsm.admin.usecase.dto.applicant.ExcelUserInfo;
import kr.hs.entrydsm.admin.usecase.dto.excel.ExcelUser;
import kr.hs.entrydsm.admin.integrate.user.UserRepository;
import kr.hs.entrydsm.admin.presenter.excel.AdmissionTicket;
import kr.hs.entrydsm.admin.presenter.excel.ApplicantInformation;
import kr.hs.entrydsm.admin.usecase.dto.excel.ExcelUserScore;
import kr.hs.entrydsm.admin.usecase.exception.ApplicationPeriodNotOverException;
import kr.hs.entrydsm.admin.usecase.exception.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ExcelServiceManager implements ExcelService {

    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final ScoreRepository scoreRepository;
    private final ScheduleRepository scheduleRepository;

    private final AmazonS3 s3;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Override
    public void createAdmissionTicket(HttpServletResponse response) throws IOException {
        AdmissionTicket admissionTicket = null;
        
        List<Long> applicantReceiptCodes = userRepository.getUserReceiptCodes();
        int i = 0, j = 0, count = 1;
        LocalDate now = LocalDate.now();
        Schedule endDate = scheduleRepository.findByYearAndType(String.valueOf(now.getYear()), Type.END_DATE)
                .orElseThrow(ScheduleNotFoundException::new);
        if(now.isBefore(endDate.getDate())) {
            throw new ApplicationPeriodNotOverException();
        }
        
        for(Long receiptCode : applicantReceiptCodes) {
            UserInfo userInfo = userRepository.getUserInfo(receiptCode);
            ApplicantInfo applicantInfo = applicationRepository.getApplicantInfo(receiptCode);

            String examCode = userInfo.getExamCode();
            String name = userInfo.getName();
            String middleSchool = applicantInfo.getSchoolName();
            String area = userInfo.getIsDaejeon()?"대전":"전국";
            String applicationType = getApplicationType(userInfo.getApplicationType());

            byte[] imageBytes = getObject("images/" + userInfo.getPhotoFileName());

            admissionTicket = new AdmissionTicket(examCode, name, middleSchool, area, applicationType, String.valueOf(receiptCode));
            if(count % 3 ==0) {
                i++;
                j = 0;
            } else {
                j++;
            } count++;
            admissionTicket.format(i,j);

            int index = admissionTicket.getWorkbook().addPicture(imageBytes, HSSFWorkbook.PICTURE_TYPE_PNG);
            HSSFPatriarch patriarch = admissionTicket.getSheet().createDrawingPatriarch();
            HSSFClientAnchor anchor;
            anchor = new HSSFClientAnchor(i,j,i,j,(short)0,2,(short)2,14);
            anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
            patriarch.createPicture(anchor, index);
        }

        response.setContentType("ms-vnd/excel");
        String formatFilename = "attachment;filename=\"";
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일_HH시mm분"));
        String fileName = new String((formatFilename + time + "수험표.xls\"").getBytes("KSC5601"), "8859_1");
        response.setHeader("Content-Disposition", fileName);

        admissionTicket.getWorkbook().write(response.getOutputStream());
    }

    @Override
    public void createApplicantInformation(HttpServletResponse response) throws IOException {
        getApplicationInformation(response);
    }

    private void getApplicationInformation(HttpServletResponse response) throws IOException {
        ApplicantInformation applicantInformation = new ApplicantInformation();
        applicantInformation.format();
        Sheet sheet = applicantInformation.getSheet();
        List<ExcelUser> excelApplicants = userRepository.findAllForExcel();

        for(int i = 0; i < excelApplicants.size() ; i++) {
            ExcelUserScore excelUserScore = scoreRepository.findUserScore(excelApplicants.get(i).getReceiptCode());
            ExcelUserInfo excelUserInfo = applicationRepository.getExcelUserInfo(excelApplicants.get(i).getReceiptCode());

            Row row = sheet.createRow(i);

            row.createCell(0).setCellValue(excelApplicants.get(i).getExamCode());
            row.createCell(1).setCellValue(excelApplicants.get(i).getReceiptCode());
            row.createCell(2).setCellValue(excelApplicants.get(i).getApplicationType());

            row.createCell(3).setCellValue(excelApplicants.get(i).getArea());
            row.createCell(4).setCellValue(excelApplicants.get(i).getApplicationRemark());
            row.createCell(5).setCellValue(excelApplicants.get(i).getName());
            row.createCell(6).setCellValue(excelApplicants.get(i).getBirthDay());
            row.createCell(7).setCellValue(excelApplicants.get(i).getAddress());
            row.createCell(8).setCellValue(excelApplicants.get(i).getTelephoneNumber());
            row.createCell(9).setCellValue(excelApplicants.get(i).getSex());
            row.createCell(10).setCellValue(excelApplicants.get(i).getEducationalStatus());
            row.createCell(11).setCellValue(excelUserInfo.getYearOfGraduation());
            row.createCell(12).setCellValue(excelUserInfo.getMiddleSchool());
            row.createCell(13).setCellValue(excelUserInfo.getMiddleSchoolStudentNumber());
            row.createCell(14).setCellValue(excelApplicants.get(i).getParentName());
            row.createCell(15).setCellValue(excelApplicants.get(i).getParentTel());

            String[] koreanScore = excelUserScore.getKoreanGrade().split("");

            row.createCell(16).setCellValue(koreanScore[0]);
            row.createCell(17).setCellValue(koreanScore[1]);
            row.createCell(18).setCellValue(koreanScore[2]);
            row.createCell(19).setCellValue(koreanScore[3]);
            row.createCell(20).setCellValue(koreanScore[4]);
            row.createCell(21).setCellValue(koreanScore[5]);

            String[] socialScore = excelUserScore.getSocialGrade().split("");

            row.createCell(22).setCellValue(socialScore[0]);
            row.createCell(23).setCellValue(socialScore[1]);
            row.createCell(24).setCellValue(socialScore[2]);
            row.createCell(25).setCellValue(socialScore[3]);
            row.createCell(26).setCellValue(socialScore[4]);
            row.createCell(27).setCellValue(socialScore[5]);

            String[] historyScore = excelUserScore.getHistoryGrade().split("");

            row.createCell(28).setCellValue(historyScore[0]);
            row.createCell(29).setCellValue(historyScore[1]);
            row.createCell(30).setCellValue(historyScore[2]);
            row.createCell(31).setCellValue(historyScore[3]);
            row.createCell(32).setCellValue(historyScore[4]);
            row.createCell(33).setCellValue(historyScore[5]);

            String[] mathScore = excelUserScore.getMathGrade().split("");

            row.createCell(34).setCellValue(mathScore[0]);
            row.createCell(35).setCellValue(mathScore[1]);
            row.createCell(36).setCellValue(mathScore[2]);
            row.createCell(37).setCellValue(mathScore[3]);
            row.createCell(38).setCellValue(mathScore[4]);
            row.createCell(39).setCellValue(mathScore[5]);

            String[] scienceScore = excelUserScore.getScienceGrade().split("");

            row.createCell(40).setCellValue(scienceScore[0]);
            row.createCell(41).setCellValue(scienceScore[1]);
            row.createCell(42).setCellValue(scienceScore[2]);
            row.createCell(43).setCellValue(scienceScore[3]);
            row.createCell(44).setCellValue(scienceScore[4]);
            row.createCell(45).setCellValue(scienceScore[5]);

            String[] techAndHomeScore = excelUserScore.getTechAndHomeGrade().split("");

            row.createCell(46).setCellValue(techAndHomeScore[0]);
            row.createCell(47).setCellValue(techAndHomeScore[1]);
            row.createCell(48).setCellValue(techAndHomeScore[2]);
            row.createCell(49).setCellValue(techAndHomeScore[3]);
            row.createCell(50).setCellValue(techAndHomeScore[4]);
            row.createCell(51).setCellValue(techAndHomeScore[5]);

            String[] englishScore = excelUserScore.getEnglishGrade().split("");

            row.createCell(52).setCellValue(englishScore[0]);
            row.createCell(53).setCellValue(englishScore[0]);
            row.createCell(54).setCellValue(englishScore[0]);
            row.createCell(55).setCellValue(englishScore[0]);
            row.createCell(56).setCellValue(englishScore[0]);
            row.createCell(57).setCellValue(englishScore[0]);

            row.createCell(58).setCellValue(excelUserScore.getTotalFirstGradeScores().toString());
            row.createCell(59).setCellValue(excelUserScore.getTotalSecondGradeScores().toString());
            row.createCell(60).setCellValue(excelUserScore.getTotalThirdGradeScores().toString());
            row.createCell(61).setCellValue(excelUserScore.getConversionScore().toString());

            row.createCell(62).setCellValue(excelUserScore.getVolunteerTime());
            row.createCell(63).setCellValue(excelUserScore.getVolunteerScore().toString());

            row.createCell(64).setCellValue(excelUserScore.getDayAbsenceCount());
            row.createCell(65).setCellValue(excelUserScore.getLatenessCount());
            row.createCell(66).setCellValue(excelUserScore.getLectureAbsenceCount());
            row.createCell(67).setCellValue(excelUserScore.getEarlyLeaveCount());

            row.createCell(68).setCellValue(excelUserScore.getAttendanceScore());

            row.createCell(69).setCellValue(excelUserScore.getTotalScoreFirstRound().toString());

            row.createCell(70).setCellValue(excelApplicants.get(i).getSelfIntroduce());
            row.createCell(71).setCellValue(excelApplicants.get(i).getSelfIntroduce());
        }

        response.setContentType("ms-vnd/excel");
        String formatFilename = "attachment;filename=\"지원자 목록";
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일_HH시mm분"));
        String fileName = new String((formatFilename + time + ".xls\"").getBytes("KSC5601"), "8859_1");
        response.setHeader("Content-Disposition", fileName);

        applicantInformation.getWorkbook().write(response.getOutputStream());
    }

    private byte[] getObject(String fileName) throws IOException {
        S3Object object = s3.getObject(bucket, fileName);
        return IOUtils.toByteArray(object.getObjectContent());
    }

    private String getApplicationType(String applicationType) {
        switch (applicationType) {
            case "COMMON":
                return "일반전형";
            case "MEISTER":
                return "마이스터전형";
            case "SOCIAL":
                return "사회통합전형";
            default:
                return null;
        }
    }

}
