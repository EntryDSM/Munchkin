package kr.hs.entrydsm.application.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Getter
public class TotalGrade {

    @NotNull
    private final SubjectScore subjectScore;

    @NotNull
    private final EtcScore etcScore;

    public static TotalGrade from(SubjectScore subjectScore, EtcScore etcScore) {
        return TotalGrade.builder()
                .subjectScore(subjectScore)
                .etcScore(etcScore)
                .build();
    }
}
