package kr.hs.entrydsm.application.usecase.pdf;

import kr.hs.entrydsm.application.entity.*;
import kr.hs.entrydsm.application.usecase.dto.CalculatedScore;
import kr.hs.entrydsm.application.usecase.dto.PdfData;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
import kr.hs.entrydsm.application.usecase.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class PdfDataConverter {

    private final GraduationApplicationRepository graduationRepository;
    private final QualificationExamApplicationRepository qualificationExamRepository;
    private final ImageService imageService;

    public PdfData applicationToInfo(Applicant applicant, CalculatedScore calculatedScore) {
		Map<String, Object> values = new HashMap<>();
        setReceiptCode(applicant, values);
        setEntranceYear(values);
        setPersonalInfo(applicant, values);
        setGenderInfo(applicant, values);
        setSchoolInfo(applicant, values);
        setPhoneNumber(applicant, values);
        setGraduationClassification(applicant, values);
        setUserType(applicant, values);
        setGradeScore(applicant, calculatedScore, values);
        setLocalDate(values);
        setIntroduction(applicant, values);
        setParentInfo(applicant, values);

        if (applicant.isRecommendationsRequired()) {
            setRecommendations(applicant, values);
        }

        if (applicant.getPhotoFileName() != null && !applicant.getPhotoFileName().isBlank()) {
            setBase64Image(applicant, values);
        }

        return new PdfData(values);
    }

    private void setReceiptCode(Applicant applicant, Map<String, Object> values) {
        values.put("receiptCode", applicant.getReceiptCode());
    }

    private void setEntranceYear(Map<String, Object> values) {
        int entranceYear = LocalDate.now().plusYears(1).getYear();
        values.put("entranceYear", String.valueOf(entranceYear));
    }

    private void setPersonalInfo(Applicant applicant, Map<String, Object> values) {
        values.put("userName", setBlankIfNull(applicant.getName()));
        values.put("isMale", toBallotBox(applicant.isMale()));
        values.put("isFemale", toBallotBox(applicant.isFemale()));
        values.put("address", setBlankIfNull(applicant.getAddress()));
        values.put("detailAddress", setBlankIfNull(applicant.getDetailAddress()));

        String birthday = "";
        if (applicant.getBirthday() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
            birthday = applicant.getBirthday().format(formatter);
        }
        values.put("birthday", birthday);
    }

    private void setGenderInfo(Applicant applicant, Map<String, Object> values) {
        String gender = null;
        if (applicant.isFemale()) {
            gender = "여";
        } else if (applicant.isMale()) {
            gender = "남";
        }
        values.put("gender", setBlankIfNull(gender));
    }

    private void setSchoolInfo(Applicant applicant, Map<String, Object> values) {
        if (applicant.hasSchoolInfo()) {
            GraduationApplication application = graduationRepository.findByReceiptCode(applicant.getReceiptCode())
                    .orElseThrow(ApplicationNotFoundException::new);
            values.put("schoolCode", setBlankIfNull(application.getSchoolCode()));
            values.put("schoolClass", setBlankIfNull(application.getSchoolClass()));
            values.put("schoolTel", toFormattedPhoneNumber(application.getSchoolTel()));
            values.put("schoolName", setBlankIfNull(application.getSchoolName()));
        } else {
            values.putAll(emptySchoolInfo());
        }
    }

    private void setPhoneNumber(Applicant applicant, Map<String, Object> values) {
        values.put("applicantTel", toFormattedPhoneNumber(applicant.getTelephoneNumber()));
        values.put("parentTel", toFormattedPhoneNumber(applicant.getParentTel()));
        String homeTel = applicant.isHomeTelEmpty() ? "없음" : toFormattedPhoneNumber(applicant.getHomeTel());
        values.put("homeTel", toFormattedPhoneNumber(homeTel));
    }

    private void setGraduationClassification(Applicant applicant, Map<String, Object> values) {
        values.putAll(emptyGraduationClassification());

        switch (applicant.getEducationalStatus()) {
            case EducationalStatus.QUALIFICATION_EXAM:
                qualificationExamRepository.findByReceiptCode(applicant.getReceiptCode())
                        .filter(qualificationExam -> qualificationExam.getQualifiedAt() != null)
                        .ifPresent(qualificationExam -> {
                            values.put("qualificationExamPassedYear", String.valueOf(qualificationExam.getQualifiedAt().getYear()));
                            values.put("qualificationExamPassedMonth", String.valueOf(qualificationExam.getQualifiedAt().getMonthValue()));
                        });
                break;
            case EducationalStatus.GRADUATE:
                graduationRepository.findByReceiptCode(applicant.getReceiptCode())
                        .filter(graduation -> graduation.getGraduatedAt() != null)
                        .ifPresent(graduation -> {
                            values.put("graduateYear", String.valueOf(graduation.getGraduatedAt().getYear()));
                            values.put("graduateMonth", String.valueOf(graduation.getGraduatedAt().getMonthValue()));
                        });
                break;
            case EducationalStatus.PROSPECTIVE_GRADUATE:
                graduationRepository.findByReceiptCode(applicant.getReceiptCode())
                        .filter(graduation -> graduation.getGraduatedAt() != null)
                        .ifPresent(graduation -> {
                            values.put("prospectiveGraduateMonth", String.valueOf(graduation.getGraduatedAt().getMonthValue()));
                        });
                break;
        }
    }

    private void setUserType(Applicant applicant, Map<String, Object> values) {
        values.put("isQualificationExam", toBallotBox(applicant.isQualificationExam()));
        values.put("isGraduate", toBallotBox(applicant.isGraduate()));
        values.put("isProspectiveGraduate", toBallotBox(applicant.isProspectiveGraduate()));
        values.put("isDaejeon", toBallotBox(applicant.getIsDaejeon()));
        values.put("isNotDaejeon", toBallotBox(!applicant.getIsDaejeon()));
        values.put("isBasicLiving", toBallotBox(applicant.isBasicLiving()));
        values.put("isFromNorth", toBallotBox(applicant.isFromNorth()));
        values.put("isLowestIncome", toBallotBox(applicant.isLowestIncome()));
        values.put("isMulticultural", toBallotBox(applicant.isMulticultural()));
        values.put("isOneParent", toBallotBox(applicant.isOneParent()));
        values.put("isTeenHouseholder", toBallotBox(applicant.isTeenHouseholder()));
        values.put("isPrivilegedAdmission", toBallotBox(applicant.isPrivilegedAdmission()));
        values.put("isNationalMerit", toBallotBox(applicant.isNationalMerit()));
        values.put("isCommon", toBallotBox(applicant.isCommonApplicationType()));
        values.put("isMeister", toBallotBox(applicant.isMeisterApplicationType()));
        values.put("isSocialMerit", toBallotBox(applicant.isSocialApplicationType()));
    }

    private void setGradeScore(Applicant applicant, CalculatedScore calculatedScore, Map<String, Object> values) {
        values.put("conversionScore1st", applicant.isQualificationExam() ? "" : calculatedScore.getTotalThirdBeforeBeforeScore().toString());
        values.put("conversionScore2nd", applicant.isQualificationExam() ? "" : calculatedScore.getTotalThirdBeforeScore().toString());
        values.put("conversionScore3rd", applicant.isQualificationExam() ? "" : calculatedScore.getTotalThirdGradeScore().toString());
        values.put("conversionScore", calculatedScore.getConversionScore().toString());
        values.put("attendanceScore", String.valueOf(calculatedScore.getAttendanceScore()));
        values.put("volunteerScore", calculatedScore.getVolunteerScore().toString());
        values.put("finalScore", calculatedScore.getTotalScoreFirstRound().toString());
    }

    private void setLocalDate(Map<String, Object> values) {
        LocalDateTime now = LocalDateTime.now();
        values.put("year", String.valueOf(now.getYear()));
        values.put("month", String.valueOf(now.getMonthValue()));
        values.put("day", String.valueOf(now.getDayOfMonth()));
    }

    private void setIntroduction(Applicant applicant, Map<String, Object> values) {
        values.put("selfIntroduction", setBlankIfNull(applicant.getSelfIntroduce()));
        values.put("studyPlan", setBlankIfNull(applicant.getStudyPlan()));
        values.put("newLineChar", "\n");
    }

    private void setParentInfo(Applicant applicant, Map<String, Object> values) {
        values.put("parentName", applicant.getParentName());
    }

    private void setRecommendations(Applicant applicant, Map<String, Object> values) {
        values.put("isDaejeonAndMeister", markIfTrue(applicant.getIsDaejeon() && applicant.isMeisterApplicationType()));
        values.put("isDaejeonAndSocialMerit", markIfTrue(applicant.getIsDaejeon() && applicant.isSocialApplicationType()));
        values.put("isNotDaejeonAndMeister", markIfTrue(!applicant.getIsDaejeon() && applicant.isMeisterApplicationType()));
        values.put("isNotDaejeonAndSocialMerit", markIfTrue(!applicant.getIsDaejeon() && applicant.isSocialApplicationType()));
    }

    private void setBase64Image(Applicant applicant, Map<String, Object> values) {
        try {
			System.out.println(applicant.getReceiptCode());
			System.out.println(applicant.getPhotoFileName());
            byte[] imageBytes = imageService.getObject("images/" + applicant.getPhotoFileName());
            String base64EncodedImage = new String(Base64.getEncoder().encode(imageBytes), StandardCharsets.UTF_8);
            values.put("base64Image", base64EncodedImage);
        } catch (IOException ignored) {}
    }

    private String markIfTrue(boolean isTrue) {
        return isTrue ? "◯" : "";
    }

    private Map<String, Object> emptySchoolInfo() {
        return Map.of(
                "schoolCode", "",
                "schoolClass", "",
                "schoolTel", "",
                "schoolName", ""
        );
    }

    private Map<String, Object> emptyGraduationClassification() {
        return Map.of(
                "qualificationExamPassedYear", "20__",
                "qualificationExamPassedMonth", "__",
                "graduateYear", "20__",
                "graduateMonth", "__",
                "prospectiveGraduateMonth", "__"
        );
    }

    private String toFormattedPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return "";
        } else {
            return phoneNumber.replace("-", " - ");
        }
    }

    private String setBlankIfNull(String input) {
        return input == null ? "" : input;
    }

    private String toBallotBox(boolean is) {
        return is ? "☑" : "☐";
    }
}
