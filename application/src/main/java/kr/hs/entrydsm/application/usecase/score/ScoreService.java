package kr.hs.entrydsm.application.usecase.score;

import kr.hs.entrydsm.application.usecase.dto.score.request.EtcScoreRequest;
import kr.hs.entrydsm.application.usecase.dto.score.request.GedScoreRequest;
import kr.hs.entrydsm.application.usecase.dto.score.request.SubjectScoreRequest;
import kr.hs.entrydsm.application.usecase.dto.score.response.EtcScoreResponse;
import kr.hs.entrydsm.application.usecase.dto.score.response.GedScoreResponse;
import kr.hs.entrydsm.application.usecase.dto.score.response.SubjectScoreResponse;

public interface ScoreService {

    void updateSubjectScore(SubjectScoreRequest score);

    void updateEtcScore(EtcScoreRequest score);

    void updateGedScore(GedScoreRequest score);

    SubjectScoreResponse getSubjectScore();

    EtcScoreResponse getEtcScore();

    GedScoreResponse getGedScore();

}
