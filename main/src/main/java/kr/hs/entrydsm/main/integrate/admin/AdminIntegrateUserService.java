package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.admin.usecase.dto.Applicant;
import kr.hs.entrydsm.admin.usecase.dto.ExcelUser;
import kr.hs.entrydsm.admin.usecase.dto.SaveExamCodeUserResponse;
import kr.hs.entrydsm.application.integrate.admin.ApplicationExportRepository;
import kr.hs.entrydsm.application.usecase.dto.ReportCard;
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
                                   boolean isPrintedArrived) {
        Page<User> users = userExportRepository.findAll(page, receiptCode, isDaejeon, isNationwide, telephoneNumber, name, isCommon, isMeister, isSocial, isPrintedArrived);
        long totalElements = users.getTotalElements();
        List<Applicant> applicants = new ArrayList<>();
        for (User user : users) {
            applicants.add(
                    Applicant.builder()
                            .receiptCode(user.getReceiptCode())
                            .name(user.getName())
                            .isDaejeon(user.isDaejeon())
                            .applicationType(String.valueOf(user.getApplicationType()))
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
    public List<SaveExamCodeUserResponse> findAllIsSubmitTrue() {
        List<User> users = userExportRepository.findAllIsSubmitTrue();
        List<SaveExamCodeUserResponse> applicants = new ArrayList<>();
        for (User user : users) {
            applicants.add(
                    SaveExamCodeUserResponse.builder()
                            .applicationType(user.getApplicationType().toString())
                            .isDaejeon(user.isDaejeon())
                            .address(user.getAddress())
                            .receiptCode(user.getReceiptCode())
                            .build()
            );
        }

        return applicants;
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
                .email(user.getEmail())
                .applicationType(String.valueOf(user.getApplicationType()))
                .address(user.getAddress())
                .detailAddress(null)
                .birthDate(user.getBirthday())
                .isPrintedArrived(user.getStatus().isPrintedArrived())
                .isSubmit(user.getStatus().isSubmit())
                .telephoneNumber(user.getTelephoneNumber())
                .parentTel(user.getParentTel())
                .homeTel(user.getHomeTel())
                .selfIntroduce(user.getSelfIntroduce())
                .photoFileName(user.getPhotoFileName())
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
        userExportRepository.changeIsPrintedArrived(receiptCode, isPrintedArrived);
    }

    @Override
    public List<ExcelUser> findAllForExcel() {
        List<User> users = userExportRepository.findAllForExcel();

        List<ExcelUser> excelUsers = new ArrayList<>();
        for (User user : users) {
            excelUsers.add(
                    ExcelUser.builder()
                            .examCode(user.getStatus().getExamCode()) //수험번호
                            .receiptCode(user.getReceiptCode()) //접수 번호
                            .applicationType(String.valueOf(user.getApplicationType())) //전형 유형
                            .applicationRemrk(String.valueOf(user.getApplicationRemark())) //추가 유형
                            .area(String.valueOf(user.isDaejeon())) //지역
                            .name(user.getName()) //이름
                            .birthDay(user.getBirthday().toString()) //생년월일
                            .sex(user.getSex().toString()) //성별
                            .address(user.getAddress()) //주소
                            .educationalStatus(user.getEducationalStatus().toString()) //학력구분
                            .telephoneNumber(user.getTelephoneNumber()) //학생 전화번호
                            .studyPlan(user.getStudyPlan()) //자기소개서
                            .selfIntroduce(user.getSelfIntroduce()) //학업 계획서
                            .parentName(user.getParentName()) //보호자 이름
                            .parentTel(user.getParentTel()) //보호자 전화번호
                    .build()
            );
        }

        return excelUsers;
    }

}