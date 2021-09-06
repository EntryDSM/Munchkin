package kr.hs.entrydsm.application.usecase.dto.application.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {

    @NotEmpty(message = "education_status는 Null 또는 공백을 허용하지 않습니다.")
    private String educationalStatus;

    @NotEmpty(message = "application_type은 Null 또는 공백을 허용하지 않습니다.")
    private String applicationType;

    @NotNull(message = "is_daejeon은 Null을 허용하지 않습니다.")
    @JsonProperty(value = "is_daejeon")
    private Boolean isDaejeon;

    private String applicationRemark;

    @Length(min = 6, max = 6, message = "INVALID DATE")
    private String graduatedAt;

    private String headcount;

}
