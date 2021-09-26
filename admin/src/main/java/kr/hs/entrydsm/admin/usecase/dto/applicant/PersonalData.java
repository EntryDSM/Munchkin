package kr.hs.entrydsm.admin.usecase.dto.applicant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalData {

    private String photoUrl;

    private String name;

    private String email;

    private String birthDate;

    private String schoolName;

    private Boolean isGraduated;

    private String educationalStatus;

    private String applicationType;

    private String address;

    private String detailAddress;

    private String telephoneNumber;

    private String parentTel;

    private String schoolTel;

    private String homeTel;

    private String applicationRemark;

    private String headcount;

}
