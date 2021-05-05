package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.admin.usecase.dto.Applicant;
import kr.hs.entrydsm.application.integrate.admin.ApplicationExportRepository;
import kr.hs.entrydsm.common.model.ReportCard;
import kr.hs.entrydsm.user.entity.user.User;
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
    public Page<Applicant> findAll(Pageable page, Long receiptCode,
                                   boolean isDaejeon, boolean isNationwide,
                                   String telephoneNumber, String name,
                                   boolean isCommon, boolean isMeister, boolean isSocial,
                                   boolean isPrintedArrived, boolean isPaid) {
        Page<User> users = userExportRepository.findAll(page, receiptCode, isDaejeon, isNationwide, telephoneNumber, name, isCommon, isMeister, isSocial, isPrintedArrived, isPaid);
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
        return new PageImpl<>(applicants, page, totalElements);

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

    //지원자 목록, 상세 보기
    @Override
    public Applicant getUserInfo(long receiptCode) {
        User user = userExportRepository.findByReceiptCode((int) receiptCode);

        ReportCard reportCard = applicationExportRepository.getReportCard(receiptCode);

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
                .averageScore(reportCard.getAverageScore()) // 검정고시 평균 점수
                .isGraduated(reportCard.getIsGraduated()) // 검x 졸업 여부
                .volunteerTime(reportCard.getVolunteerTime()) // 검X 봉사점수
                .schoolTel(reportCard.getSchoolTel()) //검x 학교 전화번호
                .schoolName(reportCard.getSchoolName()) // 검x 학교 이름
                .latenessCount(reportCard.getLatenessCount()) // 지각 횟수
                .earlyLeaveCount(reportCard.getEarlyLeaveCount()) // 조퇴 횟수
                .lectureAbsenceCount(reportCard.getLectureAbsenceCount()) // 무단 결과
                .dayAbsenceCount(reportCard.getDayAbsenceCount()) // 무단 결석
                .conversionScore(reportCard.getTotalScore()) // 총 점수
                .build();
    }

    @Override
    public void changeIsPrintedArrived(int receiptCode, boolean isPrintedArrived) {

    }

    @Override
    public void changeIsPaid(int receiptCode, boolean isPaid) {

    }

    @Override
    public void changeIsSubmit(int receiptCode, boolean isSubmit) {

    }

}