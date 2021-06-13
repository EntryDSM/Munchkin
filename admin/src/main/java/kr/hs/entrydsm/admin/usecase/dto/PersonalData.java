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

    private String photoFileName; //사진 경로

    private String name; //이름

    private String email; //이메일

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate; //생일

    private String schoolName; //학교이름

    private boolean isGraduated; //졸업 여부

    private String educationalStatus; //학력

    private String applicationType; //전형

    private String address; //주소

    private String detailAddress; //상세주소

    private String telephoneNumber;

    private String parentTel;

    private String schoolTel;

    private String homeTel;

}
