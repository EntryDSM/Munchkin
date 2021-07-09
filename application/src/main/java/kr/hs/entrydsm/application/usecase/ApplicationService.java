package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.entity.School;
import kr.hs.entrydsm.application.usecase.dto.application.request.Information;
import kr.hs.entrydsm.application.usecase.dto.application.request.ApplicationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.request.GraduatedInformationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.response.ApplicationResponse;
import kr.hs.entrydsm.application.usecase.dto.application.response.GraduatedInformationResponse;
import kr.hs.entrydsm.application.usecase.dto.application.response.InformationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ApplicationService {

    void writeSelfIntroduce(String content);

    void writeStudyPlan(String content);

    String getSelfIntroduce();

    String getStudyPlan();

    Page<School> getSchoolsByInformation(String information, Pageable pageable);

    void writeApplicationType(ApplicationRequest applicationRequest);

    void writeGraduatedInformation(GraduatedInformationRequest information);

    void writeInformation(Information information);

    ApplicationResponse getApplicationType();

    GraduatedInformationResponse getGraduatedInformation();

    InformationResponse getInformation();

    String uploadPhoto(MultipartFile multipartFile);

//    void updateSubjectScore(SubjectScoreRequest score);
//
//    void updateEtcScore(EtcScoreRequest score);
//
//    void updateGedScore(GedScoreRequest score);
//
//    SubjectScoreResponse getSubjectScore();
//
//    EtcScoreResponse getEtcScore();
//
//    GedScoreResponse getGedScore();
//
//    TotalScoreResponse getScore();

}
