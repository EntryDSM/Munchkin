package kr.hs.entrydsm.admin.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcelUser {

    private String examCode; //수험번호

    private Long receiptCode; //접수 번호

    private String applicationType; //전형 유형

    private String applicationRemrk; //추가유형

    private String area; //지역

    private String name; //이름

    private String birthDay; //생년월일

    private String address; //주소

    private String telephoneNumber; //학생 전화번호

    private String sex; //성별

    private String educationalStatus; //학력구분

    private String studyPlan; //학업 계획서

    private String selfIntroduce; //자기소개서

    private String parentName; //보호자 이름

    private String parentTel; //보호자 전화번호
}