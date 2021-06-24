package kr.hs.entrydsm.application.usecase.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Information {

    private static final String TEL_REGEXP = "^\\d{3}\\d{3,4}\\d{4}$";

    @NotEmpty
    private String name;

    @Pattern(regexp = "^(MALE|FEMALE)$", message = "INVALID SEX")
    private String sex;

    @Length(min = 8, max = 8, message = "INVALID DATE")
    private String birthday;

    private String parentName;

    @NotEmpty
    @Pattern(regexp = TEL_REGEXP, message = "INVALID TEL")
    private String telephoneNumber;

    @Length(max = 11)
    @Pattern(regexp = TEL_REGEXP, message = "INVALID TEL")
    private String parentTel;

    @Length(max = 11)
    @Pattern(regexp = TEL_REGEXP, message = "INVALID TEL")
    private String homeTel;

    @Length(max = 11)
    @Pattern(regexp = TEL_REGEXP, message = "INVALID TEL")
    private String schoolTel;

    @Length(max = 300, message = "TOO LONG ADDRESS")
    private String address;

    @Length(max = 100, message = "TOO LONG DETAIL_ADDRESS")
    private String detailAddress;

    @Length(min = 5, max = 5, message = "INVALID POST_CODE")
    private String postCode;
    private String photoFileName;

    @Length(max = 7, message = "INVALID SCHOOL_CODE")
    private String schoolCode;

    @Length(max = 5, message = "INVALID STUDENT_NUMBER")
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
