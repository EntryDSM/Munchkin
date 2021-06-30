package kr.hs.entrydsm.application.usecase.dto.score.response;

import kr.hs.entrydsm.application.usecase.dto.TotalGrade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class TotalScoreResponse {

    @NotNull
    private final SubjectScoreResponse subjectScore;

    @NotNull
    private final EtcScoreResponse etcScore;

    public static TotalScoreResponse from(SubjectScoreResponse subjectScore, EtcScoreResponse etcScore) {
        return TotalScoreResponse.builder()
                .subjectScore(subjectScore)
                .etcScore(etcScore)
                .build();
    }

}
