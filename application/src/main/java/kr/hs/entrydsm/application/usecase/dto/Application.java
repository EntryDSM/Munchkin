package kr.hs.entrydsm.application.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Application {

    private String educationalStatus;

    private String applicationType;

    private boolean isDaejeon;

    private String applicationRemark;

}
