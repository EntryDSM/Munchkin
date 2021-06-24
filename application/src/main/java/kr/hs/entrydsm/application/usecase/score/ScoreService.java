package kr.hs.entrydsm.application.usecase.score;

import kr.hs.entrydsm.application.usecase.dto.EtcScore;
import kr.hs.entrydsm.application.usecase.dto.GedScore;
import kr.hs.entrydsm.application.usecase.dto.SubjectScore;

public interface ScoreService {

    void updateSubjectScore(SubjectScore score);

    void updateEtcScore(EtcScore score);

    void updateGedScore(GedScore score);

    SubjectScore getSubjectScore();

    EtcScore getEtcScore();

    GedScore getGedScore();

}
