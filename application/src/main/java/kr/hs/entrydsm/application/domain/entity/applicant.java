package kr.hs.entrydsm.application.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class applicant {

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
}
