package kr.hs.entrydsm.admin.usecase.dto;

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

    private String email;

    private String name;

    private boolean isDaejeon;

    private String photoFileName;

    private LocalDate birthDate;

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
    private BigDecimal averageScore;

    //검정고시 X
    private String schoolName;

    private Integer volunteerTime; //봉사 시간

    private BigDecimal conversionScore; //총 점수

    private Integer dayAbsenceCount; //무단 결석

    private Integer lectureAbsenceCount; //무단 결과

    private Integer earlyLeaveCount; //조퇴

    private Integer latenessCount; //지각

    private boolean isGraduated; // 졸업 여부

    private double distance; // 학교까지의 거리

}
