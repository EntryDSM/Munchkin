package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.domain.entity.Applicant;
import kr.hs.entrydsm.admin.integrate.user.ScoreRepository;
import kr.hs.entrydsm.application.integrate.admin.ApplicationExportRepository;
import kr.hs.entrydsm.common.model.ReportCard;
import kr.hs.entrydsm.user.domain.entity.User;
import kr.hs.entrydsm.user.integrate.admin.UserExportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminIntegrateApplicationService implements ScoreRepository {

    private final ApplicationExportRepository applicationExportRepository;
    private final UserExportRepository userExportRepository;

    //지원자 목록, 상세 보기
    @Override
    public Applicant getUserInfo(long receiptCode) {
        ReportCard reportCard = applicationExportRepository.getReportCard(receiptCode);
        User user = userExportRepository.findByReceiptCode((int) receiptCode);

        return Applicant.builder()
                .receiptCode(user.getReceiptCode())
                .name(user.getName())
                .photoFileName(user.getPhotoFileName())
                .applicationType(String.valueOf(user.getApplicationType()))
                .address(user.getAddress())
                .birthDate(user.getBirthday())
                .isPaid(user.getStatus().isPaid())
                .isPrintedArrived(user.getStatus().isPrintedArrived())
                .isSubmit(user.getStatus().isSubmit())
                .telephoneNumber(user.getTelephoneNumber())
                .parentTel(user.getParentTel())
                .homeTel(user.getHomeTel())
                .selfIntroduce(user.getSelfIntroduce())
                .studyPlan(user.getStudyPlan())
                //
                .averageScore() // 검정고시 평균 점수
                .isGraduated() // 검x 졸업 여부
                .volunteerTime() // 검X 봉사점수
                .schoolTel() //검x 학교 전화번호
                .schoolName() // 검x 학교 이름
                .latenessCount() // 지각 횟수
                .earlyLeaveCount() // 조퇴 횟수
                .lectureAbsenceCount() // 무단 결과
                .dayAbsenceCount() // 무단 결석

                .conversionScore(reportCard.getTotalScore())
                .build();

    }

}
