package kr.hs.entrydsm.application.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Applicant {

    private long receiptCode;

    private String telephoneNumber;

    private String applicationType;

    private String applicationRemark;

    private String educationalStatus;

    private boolean isDaejeon;

    private String name;

    private String sex;

    private LocalDate birthday;

    private String parentName;

    private String parentTel;

    private String address;

    private String postCode;

    private String photoFileName;

    private String homeTel;

    public boolean isMale() {
        return sex.equals("male");
    }

    public boolean isFemale() {
        return sex.equals("female");
    }

    public boolean hasSchoolInfo() {
        return !isEducationalStatusEmpty() && !isQualificationExam();
    }

    private boolean isEducationalStatusEmpty() {
        return educationalStatus == null;
    }

    private boolean isQualificationExam() {
        return educationalStatus.equals("QUALIFICATION_EXAM");
    }

    public boolean isHomeTelEmpty() {
        return homeTel == null;
    }
}
