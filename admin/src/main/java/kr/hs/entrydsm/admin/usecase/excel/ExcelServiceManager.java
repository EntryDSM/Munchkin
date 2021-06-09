package kr.hs.entrydsm.admin.usecase.excel;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import kr.hs.entrydsm.admin.integrate.score.ScoreRepository;
import kr.hs.entrydsm.admin.usecase.dto.Applicant;
import kr.hs.entrydsm.admin.usecase.dto.ExcelUser;
import kr.hs.entrydsm.admin.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.admin.presenter.excel.AdmissionTicket;
import kr.hs.entrydsm.admin.presenter.excel.ApplicantInformation;
import kr.hs.entrydsm.admin.usecase.dto.ExcelUserScore;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ExcelServiceManager implements ExcelService {

    private final ApplicantRepository applicantRepository;
    private final ScoreRepository scoreRepository;

    private final AmazonS3 s3;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Value("${munchkin.applicant-information-path}")
    private String applicantInformationPath;

    @Value("${munchkin.admission-ticket-path}")
    private String admissionTicketPath;

    @Override
    public void createAdmissionTicket(long receiptCode) throws IOException { // 유저 수험표 만들기 "수험번호", "성명", "출신 중학교", "지역", "전형 유형", "접수 번호"
        Applicant applicant = applicantRepository.getUserInfo(receiptCode);

        String examCode = applicant.getExamCode();
        String name = applicant.getName();
        String middleSchool = applicant.getSchoolName();
        String area = applicant.isDaejeon()?"대전":"전국";
        String applicationType = applicant.getApplicationType();

        byte[] imageBytes = getObject(applicant.getPhotoFileName());

        AdmissionTicket admissionTicket = new AdmissionTicket(examCode, name, middleSchool, area, applicationType, String.valueOf(receiptCode));
        admissionTicket.format(0,0);

        int index = admissionTicket.getWorkbook().addPicture(imageBytes, HSSFWorkbook.PICTURE_TYPE_PNG);
        HSSFPatriarch patriarch = admissionTicket.getSheet().createDrawingPatriarch();
        HSSFClientAnchor anchor;
        anchor = new HSSFClientAnchor(0,0,0,0,(short)0,2,(short)2,14);
        anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
        patriarch.createPicture(anchor, index);

        admissionTicket.getWorkbook().write(new FileOutputStream(new File(admissionTicketPath, name+" 수험표.xls")));
    }

    @Override
    public void createApplicantInformation() throws IOException {
        ApplicantInformation applicantInformation = new ApplicantInformation();
        applicantInformation.format();
        Sheet sheet = applicantInformation.getSheet();
        List<ExcelUser> excelApplicants = applicantRepository.findAllForExcel();

        for(int i = 1; i < excelApplicants.size() ; i++) { //학생 수만큼
            ExcelUserScore excelUserScore = scoreRepository.findUserScore(excelApplicants.get(i-1).getReceiptCode());

            Row row = sheet.createRow(i);

            row.createCell(0).setCellValue(excelApplicants.get(i-1).getExamCode()); //수험번호
            row.createCell(1).setCellValue(excelApplicants.get(i-1).getReceiptCode()); //접수번호
            row.createCell(2).setCellValue(excelApplicants.get(i-1).getApplicationType()); //전형 유형

            String area = excelApplicants.get(i-1).getArea().equals("1")?"대전":"전국";
            row.createCell(3).setCellValue(area); //지역
            row.createCell(4).setCellValue(excelApplicants.get(i-1).getApplicationRemrk()); //추가유형
            row.createCell(5).setCellValue(excelApplicants.get(i-1).getName()); //성명
            row.createCell(6).setCellValue(excelApplicants.get(i-1).getBirthDay()); //생년월일
            row.createCell(7).setCellValue(excelApplicants.get(i-1).getAddress()); //주소
            row.createCell(8).setCellValue(excelApplicants.get(i-1).getTelephoneNumber()); //전화번호
            row.createCell(9).setCellValue(excelApplicants.get(i-1).getSex()); //성별
            row.createCell(10).setCellValue(excelApplicants.get(i-1).getEducationalStatus()); //학력구분
            row.createCell(11).setCellValue(excelUserScore.getYearOfGraduation()); //졸업년도
            row.createCell(12).setCellValue(excelUserScore.getMiddleSchool()); //출신학교
            row.createCell(13).setCellValue(excelUserScore.getMiddleSchoolStudentNumber()); //반
            row.createCell(14).setCellValue(excelApplicants.get(i-1).getParentName()); //보호자 성명
            row.createCell(15).setCellValue(excelApplicants.get(i-1).getParentTel()); //보호자 전화번호

            String[] koreanScore = excelUserScore.getKoreanGrade().split("");

            row.createCell(16).setCellValue(koreanScore[0]); //국어 1학년 1학기
            row.createCell(17).setCellValue(koreanScore[1]); //국어 1학년 2학기
            row.createCell(18).setCellValue(koreanScore[2]); //국어 2학년 1학기
            row.createCell(19).setCellValue(koreanScore[3]); //국어 2학년 2학기
            row.createCell(20).setCellValue(koreanScore[4]); //국어 3학년 1학기
            row.createCell(21).setCellValue(koreanScore[5]); //국어 3학년 2학기

            String[] socialScore = excelUserScore.getSocialGrade().split("");

            row.createCell(22).setCellValue(socialScore[0]); //사회 1학년 1학기
            row.createCell(23).setCellValue(socialScore[1]); //사회 1학년 2학기
            row.createCell(24).setCellValue(socialScore[2]); //사회 2학년 1학기
            row.createCell(25).setCellValue(socialScore[3]); //사회 2학년 2학기
            row.createCell(26).setCellValue(socialScore[4]); //사회 3학년 1학기
            row.createCell(27).setCellValue(socialScore[5]); //사회 3학년 2학기

            String[] historyScore = excelUserScore.getHistoryGrade().split("");

            row.createCell(28).setCellValue(historyScore[0]); //역사 1학년 1학기
            row.createCell(29).setCellValue(historyScore[1]); //역사 1학년 2학기
            row.createCell(30).setCellValue(historyScore[2]); //역사 2학년 1학기
            row.createCell(31).setCellValue(historyScore[3]); //역사 2학년 2학기
            row.createCell(32).setCellValue(historyScore[4]); //역사 3학년 1학기
            row.createCell(33).setCellValue(historyScore[5]); //역사 3학년 2학기

            String[] mathScore = excelUserScore.getMathGrade().split("");

            row.createCell(34).setCellValue(mathScore[0]); //수학 1학년 1학기
            row.createCell(35).setCellValue(mathScore[1]); //수학 1학년 2학기
            row.createCell(36).setCellValue(mathScore[2]); //수학 2학년 1학기
            row.createCell(37).setCellValue(mathScore[3]); //수학 2학년 2학기
            row.createCell(38).setCellValue(mathScore[4]); //수학 3학년 1학기
            row.createCell(39).setCellValue(mathScore[5]); //수학 3학년 2학기

            String[] scienceScore = excelUserScore.getScienceGrade().split("");

            row.createCell(40).setCellValue(scienceScore[0]); //과학 1학년 1학기
            row.createCell(41).setCellValue(scienceScore[1]); //과학 1학년 2학기
            row.createCell(42).setCellValue(scienceScore[2]); //과학 2학년 1학기
            row.createCell(43).setCellValue(scienceScore[3]); //과학 2학년 2학기
            row.createCell(44).setCellValue(scienceScore[4]); //과학 3학년 1학기
            row.createCell(45).setCellValue(scienceScore[5]); //과학 3학년 2학기

            String[] techAndHomeScore = excelUserScore.getTechAndHomeGrade().split("");

            row.createCell(46).setCellValue(techAndHomeScore[0]); //기술가정 1학년 1학기
            row.createCell(47).setCellValue(techAndHomeScore[1]); //기술가정 1학년 2학기
            row.createCell(48).setCellValue(techAndHomeScore[2]); //기술가정 2학년 1학기
            row.createCell(49).setCellValue(techAndHomeScore[3]); //기술가정 2학년 2학기
            row.createCell(50).setCellValue(techAndHomeScore[4]); //기술가정 3학년 1학기
            row.createCell(51).setCellValue(techAndHomeScore[5]); //기술가정 3학년 2학기

            String[] englishScore = excelUserScore.getEnglishGrade().split("");

            row.createCell(52).setCellValue(englishScore[0]); //영어 1학년 1학기
            row.createCell(53).setCellValue(englishScore[0]); //영어 1학년 2학기
            row.createCell(54).setCellValue(englishScore[0]); //영어 2학년 1학기
            row.createCell(55).setCellValue(englishScore[0]); //영어 2학년 2학기
            row.createCell(56).setCellValue(englishScore[0]); //영어 3학년 1학기
            row.createCell(57).setCellValue(englishScore[0]); //영어 3학년 2학기

            row.createCell(58).setCellValue(excelUserScore.getTotalFirstGradeScores()); //1학년 성적 총합
            row.createCell(59).setCellValue(excelUserScore.getTotalSecondGradeScores()); //2학년 성적 총합
            row.createCell(60).setCellValue(excelUserScore.getTotalThirdGradeScores()); //3학년 성적 총합
            row.createCell(61).setCellValue(excelUserScore.getConversionScore()); //교과성적환산점수

            row.createCell(62).setCellValue(excelUserScore.getVolunteerTime()); //봉사시간
            row.createCell(63).setCellValue(excelUserScore.getVolunteerScore()); //봉사점수

            row.createCell(64).setCellValue(excelUserScore.getDayAbsenceCount()); //결석
            row.createCell(65).setCellValue(excelUserScore.getLatenessCount()); //지각
            row.createCell(66).setCellValue(excelUserScore.getLectureAbsenceCount()); //조퇴
            row.createCell(67).setCellValue(excelUserScore.getEarlyLeaveCount()); //결과

            row.createCell(68).setCellValue(excelUserScore.getAttendanceScore()); //출석점수

            row.createCell(69).setCellValue(excelUserScore.getTotalScoreFirstRound()); //1차전형 총점

            row.createCell(70).setCellValue(excelApplicants.get(i-1).getSelfIntroduce()); //자기소개서
            row.createCell(71).setCellValue(excelApplicants.get(i-1).getSelfIntroduce()); //학업계획서
        }

        applicantInformation.getWorkbook().write(new FileOutputStream(new File(applicantInformationPath +"지원자 목록.xls")));
    }

    private byte[] getObject(String fileName) throws IOException {
        S3Object object = s3.getObject(bucket, fileName);
        return IOUtils.toByteArray(object.getObjectContent());
    }

}
