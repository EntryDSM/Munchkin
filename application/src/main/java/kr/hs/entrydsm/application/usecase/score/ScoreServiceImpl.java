package kr.hs.entrydsm.application.usecase.score;

import kr.hs.entrydsm.application.entity.*;
import kr.hs.entrydsm.application.usecase.dto.score.request.EtcScoreRequest;
import kr.hs.entrydsm.application.usecase.dto.score.request.GedScoreRequest;
import kr.hs.entrydsm.application.usecase.dto.score.request.SubjectScoreRequest;
import kr.hs.entrydsm.application.usecase.dto.score.response.EtcScoreResponse;
import kr.hs.entrydsm.application.usecase.dto.score.response.GedScoreResponse;
import kr.hs.entrydsm.application.usecase.dto.score.response.SubjectScoreResponse;
import kr.hs.entrydsm.application.usecase.dto.score.response.TotalScoreResponse;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
import kr.hs.entrydsm.common.context.auth.manager.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService{

    private final GraduationApplicationRepository graduationApplicationRepository;
    private final QualificationExamApplicationRepository qualificationExamApplicationRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public void updateSubjectScore(SubjectScoreRequest score) {
        long receiptCode = authenticationManager.getUserReceiptCode();
        GraduationApplication graduationApplication = getGraduationApplication(receiptCode);

        graduationApplication.setMathScore(score.getMathScore());
        graduationApplication.setEnglishScore(score.getEnglishScore());
        graduationApplication.setHistoryScore(score.getHistoryScore());
        graduationApplication.setKoreanScore(score.getKoreanScore());
        graduationApplication.setSocialScore(score.getSocialScore());
        graduationApplication.setScienceScore(score.getScienceScore());
        graduationApplication.setTechAndHomeScore(score.getTechAndHomeScore());

        graduationApplicationRepository.save(graduationApplication);
    }

    @Override
    public void updateEtcScore(EtcScoreRequest score) {
        long receiptCode = authenticationManager.getUserReceiptCode();
        GraduationApplication graduationApplication = getGraduationApplication(receiptCode);

        graduationApplication.setVolunteerTime(score.getVolunteerTime());
        graduationApplication.setDayAbsenceCount(score.getDayAbsenceCount());
        graduationApplication.setLectureAbsenceCount(score.getLectureAbsenceCount());
        graduationApplication.setLatenessCount(score.getLatenessCount());
        graduationApplication.setEarlyLeaveCount(score.getEarlyLeaveCount());

        graduationApplicationRepository.save(graduationApplication);
    }

    @Override
    public void updateGedScore(GedScoreRequest score) {
        long receiptCode = authenticationManager.getUserReceiptCode();
        QualificationExamApplication qualificationExamApplication = getQualificationExamApplication(receiptCode);
        qualificationExamApplication.setAverageScore(score.getGedAverageScore());

        qualificationExamApplicationRepository.save(qualificationExamApplication);

    }

    @Override
    public SubjectScoreResponse getSubjectScore() {
        long receiptCode = authenticationManager.getUserReceiptCode();
        GraduationApplication graduationApplication = getGraduationApplication(receiptCode);
        return SubjectScoreResponse.builder()
                .koreanScore(graduationApplication.getKoreanScore())
                .englishScore(graduationApplication.getEnglishScore())
                .mathScore(graduationApplication.getMathScore())
                .socialScore(graduationApplication.getSocialScore())
                .scienceScore(graduationApplication.getScienceScore())
                .historyScore(graduationApplication.getHistoryScore())
                .techAndHomeScore(graduationApplication.getTechAndHomeScore())
                .build();
    }

    @Override
    public EtcScoreResponse getEtcScore() {
        long receiptCode = authenticationManager.getUserReceiptCode();
        GraduationApplication graduationApplication = getGraduationApplication(receiptCode);

        return EtcScoreResponse.builder()
                .dayAbsenceCount(graduationApplication.getDayAbsenceCount())
                .earlyLeaveCount(graduationApplication.getEarlyLeaveCount())
                .latenessCount(graduationApplication.getLatenessCount())
                .lectureAbsenceCount(graduationApplication.getLectureAbsenceCount())
                .volunteerTime(graduationApplication.getVolunteerTime())
                .build();
    }

    @Override
    public GedScoreResponse getGedScore() {
        long receiptCode = authenticationManager.getUserReceiptCode();
        QualificationExamApplication qualificationExamApplication = getQualificationExamApplication(receiptCode);

        return new GedScoreResponse(qualificationExamApplication.getAverageScore());
    }

    @Override
    public TotalScoreResponse getScore() {
        long receiptCode = authenticationManager.getUserReceiptCode();
        GraduationApplication graduationApplication = getGraduationApplication(receiptCode);
        SubjectScoreResponse subject = SubjectScoreResponse.builder()
                .koreanScore(graduationApplication.getKoreanScore())
                .englishScore(graduationApplication.getEnglishScore())
                .mathScore(graduationApplication.getMathScore())
                .socialScore(graduationApplication.getSocialScore())
                .scienceScore(graduationApplication.getScienceScore())
                .historyScore(graduationApplication.getHistoryScore())
                .techAndHomeScore(graduationApplication.getTechAndHomeScore())
                .build();

        EtcScoreResponse etc = EtcScoreResponse.builder()
                .dayAbsenceCount(graduationApplication.getDayAbsenceCount())
                .earlyLeaveCount(graduationApplication.getEarlyLeaveCount())
                .latenessCount(graduationApplication.getLatenessCount())
                .lectureAbsenceCount(graduationApplication.getLectureAbsenceCount())
                .volunteerTime(graduationApplication.getVolunteerTime())
                .build();

        return new TotalScoreResponse(subject, etc);
    }

    private GraduationApplication getGraduationApplication(long receiptCode) {
        GraduationApplication graduationApplication;
        if(graduationApplicationRepository.findByReceiptCode(receiptCode).isPresent()){
            return  graduationApplicationRepository.findByReceiptCode(receiptCode)
                    .orElseThrow(ApplicationNotFoundException::new);
        }else {
            graduationApplication = new GraduationApplication();
            graduationApplication.setReceiptCode(receiptCode);
            return graduationApplication;
        }
    }

    private QualificationExamApplication getQualificationExamApplication(long receiptCode) {
        QualificationExamApplication qualificationExamApplication;
        if(qualificationExamApplicationRepository.findByReceiptCode(receiptCode).isPresent()){
            return qualificationExamApplicationRepository.findByReceiptCode(receiptCode)
                    .orElseThrow(ApplicationNotFoundException::new);
        }else {
            qualificationExamApplication = new QualificationExamApplication();
            qualificationExamApplication.setReceiptCode(receiptCode);
            return qualificationExamApplication;
        }
    }

}
