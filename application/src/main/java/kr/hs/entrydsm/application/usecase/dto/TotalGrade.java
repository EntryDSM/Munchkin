package kr.hs.entrydsm.application.usecase.dto;

import kr.hs.entrydsm.application.usecase.dto.score.request.EtcScoreRequest;
import kr.hs.entrydsm.application.usecase.dto.score.request.SubjectScoreRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Getter
public class TotalGrade {

    @NotNull
    private final SubjectScoreRequest subjectScore;

    @NotNull
    private final EtcScoreRequest etcScore;

    public static TotalGrade from(SubjectScoreRequest subjectScore, EtcScoreRequest etcScore) {
        return TotalGrade.builder()
                .subjectScore(subjectScore)
                .etcScore(etcScore)
                .build();
    }
}
