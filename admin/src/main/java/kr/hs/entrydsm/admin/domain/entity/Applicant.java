package kr.hs.entrydsm.admin.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {

    //공통
    private long receiptCode;

    private String name;

    private boolean isDaejeon;

    private String photoFileName;

    private LocalDate birthDate;

    private boolean isPaid;

    private boolean isPrintedArrived;

    private boolean isSubmit;

    private String address;

    private String detailAddress;

    private String telephoneNumber;

    private String parentTel;

    private String schoolTel;

    private String homeTel;

    private String educationalStatus;

    private String applicationType;

    private String selfIntroduce; //자기소개서

    private String studyPlan; //학업계획서

    private String examCode; // 수험번호

    private String postCode;

    //검정고시 O
    private Integer averageScore;

    //검정고시 X
    private String schoolName;

    private Integer volunteerTime; //봉사 시간

    private BigDecimal conversionScore; //총 점수
  
    private Integer conversionScore; //총 점수

    private Integer dayAbsenceCount; //무단 결석

    private Integer lectureAbsenceCount; //무단 결과

    private Integer earlyLeaveCount; //조퇴

    private Integer latenessCount; //지각

    private boolean isGraduated; // 졸업 여부

    private double distance; // 학교까지의 거리

    public void updateStatus(boolean isPrintedArrived, boolean isPaid, boolean isSubmit) {
        this.isPrintedArrived = isPrintedArrived;
        this.isPaid = isPaid;
        this.isSubmit = isSubmit;
    }

    public void updateIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public void updateExamCode(String examCode) {
        this.examCode = examCode;
    }

    public void updateDistance(double distance) {
        this.distance = distance;
    }

}
