package kr.hs.entrydsm.application.usecase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Application {

    private String educationalStatus;

    private String applicationType;

    @NotNull
    @JsonProperty(value = "is_daejeon")
    private boolean isDaejeon;

    private String applicationRemark;

    private String graduatedAt;

    public Application setGraduatedAt(String graduatedAt) {
        this.graduatedAt = graduatedAt;
        return this;
    }
}
