package kr.hs.entrydsm.application.usecase.score;

import kr.hs.entrydsm.application.entity.*;
import kr.hs.entrydsm.application.usecase.dto.EtcScore;
import kr.hs.entrydsm.application.usecase.dto.GedScore;
import kr.hs.entrydsm.application.usecase.dto.SubjectScore;
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
    public void updateSubjectScore(SubjectScore score) {
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
    public void updateEtcScore(EtcScore score) {
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
    public void updateGedScore(GedScore score) {
        long receiptCode = authenticationManager.getUserReceiptCode();
        QualificationExamApplication qualificationExamApplication = getQualificationExamApplication(receiptCode);
        qualificationExamApplication.setAverageScore(score.getGedAverageScore());

        qualificationExamApplicationRepository.save(qualificationExamApplication);

    }

    @Override
    public SubjectScore getSubjectScore() {
        long receiptCode = authenticationManager.getUserReceiptCode();
        GraduationApplication graduationApplication = getGraduationApplication(receiptCode);
        return SubjectScore.builder()
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
    public EtcScore getEtcScore() {
        long receiptCode = authenticationManager.getUserReceiptCode();
        GraduationApplication graduationApplication = getGraduationApplication(receiptCode);

        return EtcScore.builder()
                .dayAbsenceCount(graduationApplication.getDayAbsenceCount())
                .earlyLeaveCount(graduationApplication.getEarlyLeaveCount())
                .latenessCount(graduationApplication.getLatenessCount())
                .lectureAbsenceCount(graduationApplication.getLectureAbsenceCount())
                .volunteerTime(graduationApplication.getVolunteerTime())
                .build();
    }

    @Override
    public GedScore getGedScore() {
        long receiptCode = authenticationManager.getUserReceiptCode();
        QualificationExamApplication qualificationExamApplication = getQualificationExamApplication(receiptCode);

        return new GedScore(qualificationExamApplication.getAverageScore());
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
