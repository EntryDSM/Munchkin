package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.admin.usecase.dto.Applicant;
import kr.hs.entrydsm.application.integrate.admin.ApplicationExportRepository;
import kr.hs.entrydsm.common.model.ReportCard;
import kr.hs.entrydsm.user.entity.User;
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
        Page<User> users = userExportRepository.findAll(page, isDaejeon, isNationwide, isPrintedArrived, isPaid, isCommon, isMeister, isSocial, receiptCode, schoolName, telephoneNumber, name);
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

}