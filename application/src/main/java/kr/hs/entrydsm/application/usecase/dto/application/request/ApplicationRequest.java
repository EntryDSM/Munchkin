package kr.hs.entrydsm.application.usecase.dto.application.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class ApplicationRequest {

    private final String educationalStatus;

    private final String applicationType;

    @NotNull
    @JsonProperty(value = "is_daejeon")
    private final boolean isDaejeon;

    private final String applicationRemark;

    @Length(min = 6, max = 6, message = "INVALID DATE")
    private final String graduatedAt;

}
