package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.usecase.dto.ExcelUser;

public class ExcelBaseTest {

    protected static final ExcelUser EXCEL_USER = ExcelUser.builder()
            .examCode("123456")
            .receiptCode(123456L)
            .applicationType("COMMON")
            .applicationRemrk(null)
            .area("대전")
            .name("홍길동")
            .birthDay("2000-00-00")
            .address("대전광역시 유성구 가정북로 76")
            .telephoneNumber("010-0000-0000")
            .sex("남자")
            .educationalStatus("졸업예정자")
            .studyPlan("학업계획서")
            .selfIntroduce("자기소개서")
            .parentName("홍상순")
            .parentTel("010-0000-0000")
            .build();



}
