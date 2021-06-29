package kr.hs.entrydsm.application.usecase.dto.application.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hs.entrydsm.application.usecase.dto.application.Information;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GraduatedInformationRequest extends Information {

    @Length(max = 11)
    @Pattern(regexp = TEL_REGEXP, message = "INVALID TEL")
    private String schoolTel;

    @Length(max = 7, message = "INVALID SCHOOL_CODE")
    private String schoolCode;

    @Length(max = 5, message = "INVALID STUDENT_NUMBER")
    private String studentNumber;

    @JsonProperty(value = "is_graduated")
    protected boolean isGraduated;

    public void setIsGraduated(boolean isGraduated) {
        this.isGraduated = isGraduated;
    }

    public void setSchoolCode(String schoolCode){
        this.schoolCode = schoolCode;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

}
