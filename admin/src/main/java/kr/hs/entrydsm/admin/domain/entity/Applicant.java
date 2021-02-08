package kr.hs.entrydsm.admin.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {

    private long receiptCode;

    private String name;

    private String photo_file_name;

    private LocalDate birthDate;

    private String schoolName;

    private String educationalStatus;

    private String applicationType;

    private String address;

    private String detailAddress;

    private String telephoneNumber;

    private String parentTel;

    private String schoolTel;

    private String homeTel;

    private Integer volunteerTime; //봉사 시간

    private Integer conversionScore; //총 점수

    private Integer dayAbsenceCount; //무단 결석

    private Integer lectureAbsenceCount; //무단 결과

    private Integer earlyLeaveCount; //조퇴

    private Integer latenessCount; //지각

    private String selfIntroduce; //자기소개서

    private String studyPlan; //학업계획서

    private boolean isPaid;

    private boolean isPrintedArrived;

    private boolean isSubmit;

}
