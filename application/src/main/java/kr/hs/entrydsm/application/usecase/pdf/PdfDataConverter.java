package kr.hs.entrydsm.application.usecase.pdf;

import kr.hs.entrydsm.application.entity.*;
import kr.hs.entrydsm.application.usecase.dto.CalculatedScore;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
import kr.hs.entrydsm.application.usecase.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class PdfDataConverter {

    private final GraduationApplicationRepository graduationRepository;
    private final QualificationExamApplicationRepository qualificationExamRepository;
    private final ImageService imageService;

    private final Map<String, Object> values = new HashMap<>();

    public Map<String, Object> applicationToInfo(Applicant applicant, CalculatedScore calculatedScore) {
        setReceiptCode(applicant);
        setEntranceYear();
        setPersonalInfo(applicant);
        setGenderInfo(applicant);
        setSchoolInfo(applicant);
        setPhoneNumber(applicant);
        setGraduationClassification(applicant);
        setUserType(applicant);
        setGradeScore(applicant, calculatedScore);
        setLocalDate();
        setIntroduction(applicant);
        setParentInfo(applicant);

        if (applicant.isRecommendationsRequired()) {
            setRecommendations(applicant);
        }

        if (applicant.getPhotoFileName() != null && !applicant.getPhotoFileName().isBlank()) {
            setBase64Image(applicant);
        }

        return values;
    }

    private void setReceiptCode(Applicant applicant) {
        values.put("receiptCode", applicant.getReceiptCode());
    }

    private void setEntranceYear() {
        int entranceYear = LocalDate.now().plusYears(1).getYear();
        values.put("entranceYear", String.valueOf(entranceYear));
    }

    private void setPersonalInfo(Applicant applicant) {
        values.put("userName", setBlankIfNull(applicant.getName()));
        values.put("isMale", toBallotBox(applicant.isMale()));
        values.put("isFemale", toBallotBox(applicant.isFemale()));
        values.put("address", setBlankIfNull(applicant.getAddress()));

        String birthday = "";
        if (applicant.getBirthday() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
            birthday = applicant.getBirthday().format(formatter);
        }
        values.put("birthday", birthday);
    }

    private void setGenderInfo(Applicant applicant) {
        String gender = null;
        if (applicant.isFemale()) {
            gender = "여";
        } else if (applicant.isMale()) {
            gender = "남";
        }
        values.put("gender", setBlankIfNull(gender));
    }

    private void setSchoolInfo(Applicant applicant) {
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

    private void setPhoneNumber(Applicant applicant) {
        values.put("applicantTel", toFormattedPhoneNumber(applicant.getTelephoneNumber()));
        values.put("parentTel", toFormattedPhoneNumber(applicant.getParentTel()));
        String homeTel = applicant.isHomeTelEmpty() ? "없음" : toFormattedPhoneNumber(applicant.getHomeTel());
        values.put("homeTel", toFormattedPhoneNumber(homeTel));
    }

    private void setGraduationClassification(Applicant applicant) {
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
                        .filter(graduation -> graduation.getGraduateAt() != null)
                        .ifPresent(graduation -> {
                            values.put("graduateYear", String.valueOf(graduation.getGraduateAt().getYear()));
                            values.put("graduateMonth", String.valueOf(graduation.getGraduateAt().getMonthValue()));
                        });
                break;
            case EducationalStatus.PROSPECTIVE_GRADUATE:
                graduationRepository.findByReceiptCode(applicant.getReceiptCode())
                        .filter(graduation -> graduation.getGraduateAt() != null)
                        .ifPresent(graduation -> {
                            values.put("prospectiveGraduateMonth", String.valueOf(graduation.getGraduateAt().getMonthValue()));
                        });
                break;
        }
    }

    private void setUserType(Applicant applicant) {
        values.put("isQualificationExam", toBallotBox(applicant.isQualificationExam()));
        values.put("isGraduate", toBallotBox(applicant.isGraduate()));
        values.put("isProspectiveGraduate", toBallotBox(applicant.isProspectiveGraduate()));
        values.put("isDaejeon", toBallotBox(applicant.isDaejeon()));
        values.put("isNotDaejeon", toBallotBox(!applicant.isDaejeon()));
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

    private void setGradeScore(Applicant applicant, CalculatedScore calculatedScore) {
        values.put("conversionScore1st", applicant.isQualificationExam() ? "" : calculatedScore.getTotalFirstGradeScore());
        values.put("conversionScore2nd", applicant.isQualificationExam() ? "" : calculatedScore.getTotalSecondGradeScore());
        values.put("conversionScore3rd", applicant.isQualificationExam() ? "" : calculatedScore.getTotalThirdGradeScore());
        values.put("conversionScore", calculatedScore.getConversionScore());
        values.put("attendanceScore", calculatedScore.getAttendanceScore());
        values.put("volunteerScore", calculatedScore.getVolunteerScore());
        values.put("finalScore", calculatedScore.getTotalScoreFirstRound());
    }

    private void setLocalDate() {
        LocalDateTime now = LocalDateTime.now();
        values.put("year", String.valueOf(now.getYear()));
        values.put("month", String.valueOf(now.getMonthValue()));
        values.put("day", String.valueOf(now.getDayOfMonth()));
    }

    private void setIntroduction(Applicant applicant) {
        values.put("selfIntroduction", setBlankIfNull(applicant.getSelfIntroduce()));
        values.put("studyPlan", setBlankIfNull(applicant.getStudyPlan()));
        values.put("newLineChar", "\n");
    }

    private void setParentInfo(Applicant applicant) {
        values.put("parentName", applicant.getParentName());
    }

    private void setRecommendations(Applicant applicant) {
        values.put("isDaejeonAndMeister", markIfTrue(applicant.isDaejeon() && applicant.isMeisterApplicationType()));
        values.put("isDaejeonAndSocialMerit", markIfTrue(applicant.isDaejeon() && applicant.isSocialApplicationType()));
        values.put("isNotDaejeonAndMeister", markIfTrue(!applicant.isDaejeon() && applicant.isMeisterApplicationType()));
        values.put("isNotDaejeonAndSocialMerit", markIfTrue(!applicant.isDaejeon() && applicant.isSocialApplicationType()));
    }

    private void setBase64Image(Applicant applicant) {
        try {
            byte[] imageBytes = imageService.getObject(applicant.getPhotoFileName());
            values.put("base64Image", Arrays.toString(Base64.getEncoder().encode(imageBytes)));
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
