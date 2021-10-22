package kr.hs.entrydsm.admin.usecase.excel;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import kr.hs.entrydsm.admin.entity.schedule.Schedule;
import kr.hs.entrydsm.admin.entity.schedule.ScheduleRepository;
import kr.hs.entrydsm.admin.entity.schedule.Type;
import kr.hs.entrydsm.admin.integrate.applicaton.ApplicationRepository;
import kr.hs.entrydsm.admin.integrate.score.ScoreRepository;
import kr.hs.entrydsm.admin.usecase.dto.applicant.ApplicantInfo;
import kr.hs.entrydsm.admin.usecase.dto.applicant.SaveExamCodeUserResponse;
import kr.hs.entrydsm.admin.usecase.dto.applicant.UserInfo;
import kr.hs.entrydsm.admin.usecase.dto.applicant.ExcelUserInfo;
import kr.hs.entrydsm.admin.usecase.dto.excel.ExcelUser;
import kr.hs.entrydsm.admin.integrate.user.UserRepository;
import kr.hs.entrydsm.admin.presenter.excel.AdmissionTicket;
import kr.hs.entrydsm.admin.presenter.excel.ApplicantInformation;
import kr.hs.entrydsm.admin.usecase.dto.excel.ExcelUserScore;
import kr.hs.entrydsm.admin.usecase.dto.score.FirstRoundSuccessfulCandidates;
import kr.hs.entrydsm.admin.usecase.dto.tmap.Coordinate;
import kr.hs.entrydsm.admin.usecase.dto.tmap.Properties;
import kr.hs.entrydsm.admin.usecase.dto.tmap.RouteBody;
import kr.hs.entrydsm.admin.usecase.dto.tmap.RouteGuidanceRequest;
import kr.hs.entrydsm.admin.usecase.dto.tmap.RouteGuidanceResponse;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class ExcelServiceManager implements ExcelService {

    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final ScoreRepository scoreRepository;
    private final ScheduleRepository scheduleRepository;

    private final AmazonS3 s3;

    private static final double SCHOOL_LATITUDE = 36.391636;
    private static final double SCHOOL_LONGITUDE = 127.363585;

    private static final String ROUTE_URL = "https://apis.openapi.sk.com/tmap/routes?version=1";
    private static final String GEO_BASIC_URL="https://apis.openapi.sk.com/tmap/geo/postcode?version=1&appKey=";

    @Value("${tmap.app.key}")
    private String appKey;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Override
    public void createAdmissionTicket(HttpServletResponse response) throws Exception {
        FirstRoundSuccessfulCandidates firstRoundSuccessfulCandidates = scoreRepository.getSuccessfulCandidateReceiptCodes();
        List<Long> passApplicationReceiptCodes = new ArrayList<>();
        passApplicationReceiptCodes.addAll(firstRoundSuccessfulCandidates.getDaejeonCommonApplicants());
        passApplicationReceiptCodes.addAll(firstRoundSuccessfulCandidates.getNationwideCommonApplicants());
        passApplicationReceiptCodes.addAll(firstRoundSuccessfulCandidates.getDaejeonMeisterApplicants());
        passApplicationReceiptCodes.addAll(firstRoundSuccessfulCandidates.getNationwideMeisterApplicants());
        passApplicationReceiptCodes.addAll(firstRoundSuccessfulCandidates.getDaejeonSocialApplicants());
        passApplicationReceiptCodes.addAll(firstRoundSuccessfulCandidates.getNationwideSocialApplicants());

        userRepository.changeIsFirstRoundPass(passApplicationReceiptCodes);


        List<Long> applicantReceiptCodes = userRepository.findAllReceiptCodeIsFirstRoundPassTrue();

        LocalDateTime now = LocalDateTime.now();
        Schedule endDate = scheduleRepository.findByYearAndType(String.valueOf(now.getYear()), Type.END_DATE)
                .orElseThrow(ScheduleNotFoundException::new);
        if(now.isBefore(endDate.getDate())) {
            throw new ApplicationPeriodNotOverException();
        } else {
            saveAllApplicantsExamCode();
            getAdmissionTicket(response, applicantReceiptCodes);
        }
    }

    @Override
    public void createApplicantInformation(HttpServletResponse response) throws IOException {
        getApplicationInformation(response);
    }

    private void getAdmissionTicket(HttpServletResponse response, List<Long> applicantReceiptCodes) throws IOException {
        AdmissionTicket admissionTicket = new AdmissionTicket();
        int x = 0, y = 0, count = 1;

        for(Long receiptCode : applicantReceiptCodes) {
            UserInfo userInfo = userRepository.getUserInfo(receiptCode);
            ApplicantInfo applicantInfo = applicationRepository.getApplicantInfo(receiptCode);

            String examCode = userInfo.getExamCode();
            String name = userInfo.getName();
            String middleSchool = applicantInfo.getSchoolName();
            String area = userInfo.getIsDaejeon()?"대전":"전국";
            String applicationType = getApplicationType(userInfo.getApplicationType());

            byte[] imageBytes = getObject("images/" + userInfo.getPhotoFileName());
            admissionTicket.format(x * 17,y * 7, examCode, name, middleSchool, area, applicationType, String.valueOf(receiptCode));

            int index = admissionTicket.getWorkbook().addPicture(imageBytes, HSSFWorkbook.PICTURE_TYPE_PNG);
            HSSFPatriarch patriarch = admissionTicket.getSheet().createDrawingPatriarch();
            HSSFClientAnchor anchor;
            anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) (y * 7),2 + (x * 17), (short) (2 + (y * 7)),14 + (x * 17));
            anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
            patriarch.createPicture(anchor, index);

            count++;
            if(count % 3 ==0) {
                x++;
                y = 0;
            } else {
                y++;
            }
        }

        response.setContentType("ms-vnd/excel");
        String formatFilename = "attachment;filename=\"";
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일_HH시mm분"));
        String fileName = new String((formatFilename + time + "수험표.xls\"").getBytes("KSC5601"), "8859_1");
        response.setHeader("Content-Disposition", fileName);

        admissionTicket.getWorkbook().write(response.getOutputStream());
    }

    private void getApplicationInformation(HttpServletResponse response) throws IOException {
        ApplicantInformation applicantInformation = new ApplicantInformation();
        Sheet sheet = applicantInformation.getSheet();
        applicantInformation.format();
        List<ExcelUser> excelApplicants = userRepository.findAllForExcel();

        for(int i = 0; i < excelApplicants.size(); i++) {
            ExcelUserScore excelUserScore = scoreRepository.findUserScore(excelApplicants.get(i).getReceiptCode());
            ExcelUserInfo excelUserInfo = applicationRepository.getExcelUserInfo(excelApplicants.get(i).getReceiptCode());

            Row row = sheet.createRow(i + 1);

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

            String[] koreanScore = getSplitScores(excelUserScore.getKoreanGrade());
            String[] socialScore = getSplitScores(excelUserScore.getSocialGrade());
            String[] historyScore = getSplitScores(excelUserScore.getHistoryGrade());
            String[] mathScore = getSplitScores(excelUserScore.getMathGrade());
            String[] scienceScore = getSplitScores(excelUserScore.getScienceGrade());
            String[] techAndHomeScore = getSplitScores(excelUserScore.getTechAndHomeGrade());
            String[] englishScore = getSplitScores(excelUserScore.getEnglishGrade());

            row.createCell(16).setCellValue(koreanScore[5]);
            row.createCell(17).setCellValue(socialScore[5]);
            row.createCell(18).setCellValue(historyScore[5]);
            row.createCell(19).setCellValue(mathScore[5]);
            row.createCell(20).setCellValue(scienceScore[5]);
            row.createCell(21).setCellValue(techAndHomeScore[5]);
            row.createCell(22).setCellValue(englishScore[5]);

            row.createCell(23).setCellValue(koreanScore[4]);
            row.createCell(24).setCellValue(socialScore[4]);
            row.createCell(25).setCellValue(historyScore[4]);
            row.createCell(26).setCellValue(mathScore[4]);
            row.createCell(27).setCellValue(scienceScore[4]);
            row.createCell(28).setCellValue(techAndHomeScore[4]);
            row.createCell(29).setCellValue(englishScore[4]);

            row.createCell(30).setCellValue(koreanScore[3]);
            row.createCell(31).setCellValue(socialScore[3]);
            row.createCell(32).setCellValue(historyScore[3]);
            row.createCell(33).setCellValue(mathScore[3]);
            row.createCell(34).setCellValue(scienceScore[3]);
            row.createCell(35).setCellValue(techAndHomeScore[3]);
            row.createCell(36).setCellValue(englishScore[3]);

            row.createCell(37).setCellValue(koreanScore[2]);
            row.createCell(38).setCellValue(socialScore[2]);
            row.createCell(39).setCellValue(historyScore[2]);
            row.createCell(40).setCellValue(mathScore[2]);
            row.createCell(41).setCellValue(scienceScore[2]);
            row.createCell(42).setCellValue(techAndHomeScore[2]);
            row.createCell(43).setCellValue(englishScore[2]);

            row.createCell(44).setCellValue(nullOrString(excelUserScore.getTotalThirdGradeScores().toString()));
            row.createCell(45).setCellValue(nullOrString((excelUserScore.getPreviousSemesterScores().toString())));
            row.createCell(46).setCellValue(nullOrString(excelUserScore.getMorePreviousSemesterScores().toString()));
            row.createCell(47).setCellValue(nullOrString(excelUserScore.getConversionScore().toString()));

            row.createCell(48).setCellValue(nullOrString(excelUserScore.getVolunteerTime().toString()));
            row.createCell(49).setCellValue(nullOrString((excelUserScore.getVolunteerScore().toString())));

            row.createCell(50).setCellValue(nullOrString(excelUserScore.getDayAbsenceCount().toString()));
            row.createCell(51).setCellValue(nullOrString(excelUserScore.getLatenessCount().toString()));
            row.createCell(52).setCellValue(nullOrString(excelUserScore.getLectureAbsenceCount().toString()));
            row.createCell(53).setCellValue(nullOrString(excelUserScore.getEarlyLeaveCount().toString()));
            row.createCell(54).setCellValue(nullOrString(excelUserScore.getAttendanceScore().toString()));

            row.createCell(55).setCellValue(excelUserScore.getTotalScoreFirstRound().toString());

            row.createCell(56).setCellValue(excelApplicants.get(i).getSelfIntroduce());
            row.createCell(57).setCellValue(excelApplicants.get(i).getSelfIntroduce());
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

    private void saveAllApplicantsExamCode() throws Exception {
        List<SaveExamCodeUserResponse> applicants = userRepository.findAllIsFirstRoundPassTrue();
        List<SaveExamCodeUserResponse> applicantSort = new ArrayList<>(applicants);
        int commonDaejeon = 1, commonNationwide = 1, meisterDaejeon = 1,
                meisterNationwide = 1, socialDaejeon = 1, socialNationwide = 1;

        for (SaveExamCodeUserResponse applicant : applicants) {
            StringBuilder examCode = new StringBuilder();
            switch (applicant.getApplicationType()) {
                case "COMMON":
                    examCode.append(1);
                    break;
                case "MEISTER":
                    examCode.append(2);
                    break;
                default:
                    examCode.append(3);
            }
            if (applicant.getIsDaejeon()) examCode.append(1);
            else examCode.append(2);

            applicant.updateExamCode(examCode.toString());
        }

        for (SaveExamCodeUserResponse applicant : applicants) {
            Coordinate coordinate = getCoordinate(applicant.getAddress());
            RouteGuidanceRequest request = RouteGuidanceRequest.builder()
                    .startX(Double.parseDouble(coordinate.getLat()))
                    .startY(Double.parseDouble(coordinate.getLon()))
                    .build();
            RouteGuidanceResponse distance = routeGuidance(request);
            applicant.updateDistance(distance.getProperties().getTotalDistance());
        }

        applicantSort.sort((o1, o2) -> Double.compare(o2.getDistance(), o1.getDistance()));

        for(SaveExamCodeUserResponse applicant : applicantSort) {
            int examOrder = 0;
            String examCode = applicant.getExamCode();
            if(examCode.startsWith("11")) {
                examOrder = commonDaejeon++;
            } else if(examCode.startsWith("12")) {
                examOrder = commonNationwide++;
            } else if(examCode.startsWith("21")) {
                examOrder = meisterDaejeon++;
            } else if(examCode.startsWith("22")) {
                examOrder = meisterNationwide++;
            } else if(examCode.startsWith("31")) {
                examOrder = socialDaejeon++;
            } else if(examCode.startsWith("32")) {
                examOrder = socialNationwide++;
            }

            applicant.updateExamCode(applicant.getExamCode() + String.format("%03d", examOrder));
            userRepository.changeExamCode(applicant.getReceiptCode(), applicant.getExamCode());
        }
    }

    private Coordinate getCoordinate(String address) throws URISyntaxException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Accept-Language", "ko");
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE
                + ";charset=UTF-8");

        URI url = new URI(GEO_BASIC_URL + appKey + "&addr=" + URLEncoder.encode(address, StandardCharsets.UTF_8) + "&addressFlag=F00&format=json");

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Map> response = restTemplate.getForObject(url, Map.class);

        Map<String, Map> coordinate =
                mapper.readValue(mapper.writeValueAsString(response.get("coordinateInfo")), Map.class);
        List<Coordinate> info = Arrays.asList(mapper.readValue(mapper.writeValueAsString(coordinate.get("coordinate")), Coordinate[].class));

        return info.get(0);
    }

    private RouteGuidanceResponse routeGuidance(RouteGuidanceRequest request) throws URISyntaxException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        RouteBody routeBody = RouteBody.builder()
                .endX(SCHOOL_LATITUDE)
                .endY(SCHOOL_LONGITUDE)
                .startX(request.getStartX())
                .startY(request.getStartY())
                .totalValue(2)
                .build();

        headers.add("Accept-Language", "ko");
        headers.add("appKey",appKey);
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        URI url = new URI(ROUTE_URL);
        ObjectMapper mapper = new ObjectMapper();

        HttpEntity<RouteBody> rq = new HttpEntity<>(routeBody, headers);
        String response = "";
        try {
            response = restTemplate.postForObject(url, rq, String.class);
        } catch(Exception e) {
            System.out.println(routeBody.toString());
            System.out.println(Objects.requireNonNull(rq.getBody()));
        }
        JsonNode document = mapper.readTree(response).path("features").get(0);
        JsonNode properties = document.path("properties");

        return RouteGuidanceResponse.builder()
                .type(document.path("type").toString())
                .properties(
                        Properties.builder()
                                .totalTime(properties.path("totalTime").asInt())
                                .totalDistance(properties.path("totalDistance").asDouble())
                                .taxiFare(properties.path("taxiFare").asInt())
                                .totalFare(properties.path("totalFare").asInt())
                                .build()
                )
                .build();
    }

    private String[] getSplitScores(String scores) {
        if(scores.equals("")) {
            return  Stream.of("", "", "", "", "", "").toArray(String[]::new);
        } else {
            return scores.split("");
        }
    }

    private String nullOrString(String str) {
        return str == null ? "" : str;
    }

}
