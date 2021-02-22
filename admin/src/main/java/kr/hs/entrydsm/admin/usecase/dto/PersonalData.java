package kr.hs.entrydsm.admin.usecase.dto;

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

    private String photoFileName;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String schoolName;

    private boolean isGraduated;

    private String educationalStatus;

    private String applicationType;

    private String address;

    private String detailAddress;

    private String telephoneNumber;

    private String parentTel;

    private String schoolTel;

    private String homeTel;

}
