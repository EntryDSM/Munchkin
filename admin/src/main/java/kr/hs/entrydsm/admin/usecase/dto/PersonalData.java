package kr.hs.entrydsm.admin.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
public class PersonalData {

    private String photo_file_name;

    private String name;

    private LocalDate birthDate;

    private String schoolName;

    private String educationalStatus;

    private String applicationType;

    private String address;

    private String detailAddress;

    private String telephoneNumber;

    private String parentTel;

    private String schoolTel;

    private String homeTel;

}
