package kr.hs.entrydsm.application;

import kr.hs.entrydsm.application.entity.*;
import kr.hs.entrydsm.application.entity.GraduationApplicationRepository;
import kr.hs.entrydsm.application.entity.QualificationExamApplicationRepository;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
import lombok.RequiredArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class PdfDataConverter {

    private final GraduationApplicationRepository graduationRepository;
    private final QualificationExamApplicationRepository qualificationExamRepository;

    private final Map<String, Object> values = new HashMap<>();

    public Map<String, Object> applicationToInfo(Applicant applicant) {
        setReceiptCode(applicant);
        setPersonalInfo(applicant);
        setGenderInfo(applicant);
        setSchoolInfo(applicant);
        setPhoneNumber(applicant);

        return values;
    }

    private void setReceiptCode(Applicant applicant) {
        values.put("receiptCode", applicant.getReceiptCode());
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
            case "QUALIFICATION_EXAM":
                // TODO 검정고시 합격 날짜 필요
                break;
            case "GRADUATE":
                graduationRepository.findByReceiptCode(applicant.getReceiptCode())
                        .filter(graduation -> graduation.getGraduateAt() != null)
                        .ifPresent(graduation -> {
                            values.put("graduateYear", String.valueOf(graduation.getGraduateAt().getYear()));
                            values.put("graduateMonth", String.valueOf(graduation.getGraduateAt().getMonthValue()));
                        });
                break;
            case "PROSPECTIVE_GRADUATE":
                graduationRepository.findByReceiptCode(applicant.getReceiptCode())
                        .filter(graduation -> graduation.getGraduateAt() != null)
                        .ifPresent(graduation -> {
                            values.put("prospectiveGraduateMonth", String.valueOf(graduation.getGraduateAt().getMonthValue()));
                        });
                break;
        }
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
