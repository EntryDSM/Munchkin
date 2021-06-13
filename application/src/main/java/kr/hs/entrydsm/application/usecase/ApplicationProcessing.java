package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.entity.School;
import kr.hs.entrydsm.application.usecase.dto.Application;
import kr.hs.entrydsm.application.usecase.dto.EtcScore;
import kr.hs.entrydsm.application.usecase.dto.Information;
import kr.hs.entrydsm.application.usecase.dto.SubjectScore;
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

    void writeApplicationType(Application applicationRequest);

    void writeInformation(Information information);

    Application getApplicationType();

    Information getInformation() throws IOException;

    String uploadPhoto(MultipartFile multipartFile) throws IOException;

    void updateSubjectScore(SubjectScore score);

    void updateEtcScore(EtcScore score);

}
