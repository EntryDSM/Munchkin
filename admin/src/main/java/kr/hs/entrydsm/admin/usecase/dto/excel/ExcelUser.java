package kr.hs.entrydsm.admin.usecase.dto.excel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcelUser {

    private String examCode;

    private Long receiptCode;

    private String applicationType;

    private String applicationRemark;

    private String area;

    private String name;

    private String birthDay;

    private String address;

    private String telephoneNumber;

    private String sex;

    private String educationalStatus;

    private String studyPlan;

    private String selfIntroduce;

    private String parentName;

    private String parentTel;
}