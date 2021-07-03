package kr.hs.entrydsm.application.usecase.dto.application.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ApplicationRequest {

    private String educationalStatus;

    private String applicationType;

    @NotNull
    @JsonProperty(value = "is_daejeon")
    private boolean isDaejeon;

    private String applicationRemark;

    @Length(min = 6, max = 6, message = "INVALID DATE")
    private String graduatedAt;

}
