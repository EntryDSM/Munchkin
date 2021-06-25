package kr.hs.entrydsm.application.usecase.dto.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ApplicationResponse {

    private String educationalStatus;

    private String applicationType;

    @JsonProperty(value = "is_daejeon")
    private boolean isDaejeon;

    private String applicationRemark;

    private String graduatedAt;

    public ApplicationResponse setGraduatedAt(String graduatedAt) {
        this.graduatedAt = graduatedAt;
        return this;
    }
}
