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
    private long receiptCode; //접수 번호

    private String email; //이메일

    private String name; //이름

    private boolean isDaejeon; //대전 사는지 여부

    private String photoFileName; //사진 경로

    private LocalDate birthDate; //생일

    private boolean isPrintedArrived; //원서 도착 여부

    private boolean isSubmit; //최종 제출 여부

    private String address; //주소

    private String detailAddress; //상세 주소

    private String telephoneNumber; //전화번호

    private String parentTel; //부모님 전화번호

    private String schoolTel; //학교 전화번호

    private String homeTel; //집 전화번호

    private String educationalStatus; //학력

    private String applicationType; //전형

    private String selfIntroduce; //자기소개서

    private String studyPlan; //학업계획서

    private String examCode; // 수험번호

    //검정고시 O
    private BigDecimal averageScore; //검정고시 평균 점수

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
