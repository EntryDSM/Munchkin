package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.entity.School;
import kr.hs.entrydsm.application.usecase.dto.application.request.ApplicationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.request.InformationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.response.ApplicationResponse;
import kr.hs.entrydsm.application.usecase.dto.application.response.InformationResponse;
import kr.hs.entrydsm.application.usecase.dto.score.request.EtcScoreRequest;
import kr.hs.entrydsm.application.usecase.dto.score.request.GedScoreRequest;
import kr.hs.entrydsm.application.usecase.dto.score.request.SubjectScoreRequest;
import kr.hs.entrydsm.application.usecase.dto.score.response.EtcScoreResponse;
import kr.hs.entrydsm.application.usecase.dto.score.response.GedScoreResponse;
import kr.hs.entrydsm.application.usecase.dto.score.response.SubjectScoreResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ApplicationProcessing {

    void writeSelfIntroduce(String content);

    void writeStudyPlan(String content);

    String getSelfIntroduce();

    String getStudyPlan();

    Page<School> getSchoolsByInformation(String information, Pageable pageable);

    void writeApplicationType(ApplicationRequest applicationRequest);

    void writeInformation(InformationRequest information);

    ApplicationResponse getApplicationType();

    InformationResponse getInformation() throws IOException;

    String uploadPhoto(MultipartFile multipartFile) throws IOException;

    void updateSubjectScore(SubjectScoreRequest score);

    void updateEtcScore(EtcScoreRequest score);

    void updateGedScore(GedScoreRequest score);

    SubjectScoreResponse getSubjectScore();

    EtcScoreResponse getEtcScore();

    GedScoreResponse getGedScore();

}
