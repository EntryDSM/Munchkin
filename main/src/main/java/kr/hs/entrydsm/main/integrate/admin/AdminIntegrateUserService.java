package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.domain.entity.Applicant;
import kr.hs.entrydsm.admin.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.application.domain.entity.Application;
import kr.hs.entrydsm.application.domain.entity.QualificationExamApplication;
import kr.hs.entrydsm.application.integrate.admin.ApplicationExportRepository;
import kr.hs.entrydsm.common.model.GraduationReportCard;
import kr.hs.entrydsm.common.model.QualificationReportCard;
import kr.hs.entrydsm.common.model.ReportCard;
import kr.hs.entrydsm.user.domain.entity.User;
import kr.hs.entrydsm.user.integrate.admin.UserExportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AdminIntegrateUserService implements ApplicantRepository {

    private final UserExportRepository userExportRepository;
    private final ApplicationExportRepository applicationExportRepository;

    @Override
    public Page<Applicant> findAll(Pageable page, boolean isDaejeon, boolean isNationwide,
                                   boolean isPrintedArrived, boolean isPaid, boolean isCommon,
                                   boolean isMeister, boolean isSocial, int receiptCode,
                                   String schoolName, String telephoneNumber, String name) {
        /*Page<User> users = userExportRepository.findAll(page);
        long totalElements = users.getTotalElements();
        List<Applicant> applicants = new ArrayList<>();
        for (User user : users) {
            applicants.add(
                    Applicant.builder()
                        .receiptCode(user.getReceiptCode())
                        .name(user.getName())
                        .isDaejeon(user.isDaejeon())
                        .applicationType(String.valueOf(user.getApplicationType()))
                        .isPaid(user.getStatus().isPaid())
                        .isPrintedArrived(user.getStatus().isPrintedArrived())
                        .isSubmit(user.getStatus().isSubmit())
                    .build()
            );
        }
        return new PageImpl<>(applicants, pageable, totalElements);*/
        return null;

    }

    @Override
    public void changeExamCode(long receiptCode, String examCode) {
        User user = userExportRepository.findByReceiptCode((int)receiptCode);
        userExportRepository.changeExamCode(user.getReceiptCode(), examCode);
    }

    @Override
    public List<Applicant> findAllIsSubmitTrue() {
        return null;
    }
  
    @Override
    public void changeStatus(int receiptCode, boolean isPrintedArrived, boolean isPaid, boolean isSubmit) {
        User user = userExportRepository.findByReceiptCode(receiptCode);
        // 상태 정보 수정 method
        // userExportRepository.method(user.getReceiptCode(), isPrintedArrived, isPaid, isSubmit);
    }

    //지원자 목록, 상세 보기
    @Override
    public Applicant getUserInfo(long receiptCode) {
        User user = userExportRepository.findByReceiptCode((int) receiptCode);

        GraduationReportCard graduationReportCard = (GraduationReportCard) applicationExportRepository.getReportCard(receiptCode);
        QualificationReportCard qualificationReportCard = (QualificationReportCard) applicationExportRepository.getReportCard(receiptCode);

        return Applicant.builder()
                .receiptCode(user.getReceiptCode())
                .name(user.getName())
                .photoFileName(user.getPhotoFileName())
                .applicationType(String.valueOf(user.getApplicationType()))
                .address(user.getAddress())
                .detailAddress(null)
                .birthDate(user.getBirthday())
                .isPaid(user.getStatus().isPaid())
                .isPrintedArrived(user.getStatus().isPrintedArrived())
                .isSubmit(user.getStatus().isSubmit())
                .telephoneNumber(user.getTelephoneNumber())
                .parentTel(user.getParentTel())
                .homeTel(user.getHomeTel())
                .selfIntroduce(user.getSelfIntroduce())
                .studyPlan(user.getStudyPlan())
                //수정 필요
                .averageScore(qualificationReportCard.getAverageScore()) // 검정고시 평균 점수
                .isGraduated(graduationReportCard.isGraduated()) // 검x 졸업 여부
                .volunteerTime(graduationReportCard.getVolunteerTime()) // 검X 봉사점수
                .schoolTel(graduationReportCard.getSchoolTel()) //검x 학교 전화번호
                .schoolName(graduationReportCard.getSchoolName()) // 검x 학교 이름
                .latenessCount(graduationReportCard.getLatenessCount()) // 지각 횟수
                .earlyLeaveCount(graduationReportCard.getEarlyLeaveCount()) // 조퇴 횟수
                .lectureAbsenceCount(graduationReportCard.getLectureAbsenceCount()) // 무단 결과
                .dayAbsenceCount(graduationReportCard.getDayAbsenceCount()) // 무단 결석
                .conversionScore(graduationReportCard.getTotalScore()) // 총 점수
                .build();
    }

}
