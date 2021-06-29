package kr.hs.entrydsm.application.usecase.dto.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponse {

    private String educationalStatus;

    private String applicationType;

    @JsonProperty(value = "is_daejeon")
    private boolean isDaejeon;

    private String applicationRemark;

    private String graduatedAt;

    @JsonProperty(value = "is_graduated")
    protected boolean isGraduated;

    public void setIsGraduated(boolean isGraduated) {
        this.isGraduated = isGraduated;
    }

    public ApplicationResponse setGraduatedAt(String graduatedAt) {
        this.graduatedAt = graduatedAt;
        return this;
    }
}
