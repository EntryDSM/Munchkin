package kr.hs.entrydsm.application.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Information {
    private String name;
    private String sex;
    private LocalDate birthday;
    private String parentName;
    private String parentTel;
    private String telephoneNumber;
    private String homeTel;
    private String address;
    private String postCode;
    private String photoFileName;

    private String schoolCode;
    private String schoolTel;

    public void setSchoolCode(String schoolCode){
        this.schoolCode = schoolCode;
    }

    public void setSchoolTel(String schoolTel){
        this.schoolTel = schoolTel;
    }

}
