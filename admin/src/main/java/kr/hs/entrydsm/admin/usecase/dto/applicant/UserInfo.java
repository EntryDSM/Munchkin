package kr.hs.entrydsm.admin.usecase.dto.applicant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private Long receiptCode;

    private String email;

    private String name;

    private Boolean isDaejeon;

    private String photoFileName;

    private LocalDate birthDate;

    private Boolean isPrintedArrived;

    private Boolean isSubmit;

    private String address;

    private String detailAddress;

    private String telephoneNumber;

    private String parentTel;

    private String homeTel;

    private String educationalStatus;

    private String applicationType;

    private String selfIntroduce;

    private String studyPlan;

    private String examCode;

    private Double distance;

}
