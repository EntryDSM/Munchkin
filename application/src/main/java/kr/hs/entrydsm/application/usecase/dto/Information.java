package kr.hs.entrydsm.application.usecase.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Information {

    @NotEmpty
    private String name;

    @NotEmpty
    private String telephoneNumber;

    private String sex;
    private String birthday;
    private String parentName;
    private String parentTel;
    private String homeTel;
    private String address;
    private String postCode;
    private String photoFileName;

    private String schoolCode;
    private String schoolTel;
    private String studentNumber;
    private boolean isGraduated;

    public void setSchoolCode(String schoolCode){
        this.schoolCode = schoolCode;
    }

    public void setSchoolTel(String schoolTel){
        this.schoolTel = schoolTel;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setIsGraduated(boolean isGraduated) {
        this.isGraduated = isGraduated;
    }

}
