package kr.hs.entrydsm.application.entity;

public class SchoolBuilder {

    public static School build() {
        return School.builder()
                .name("대전하기중학교")
                .address("대전광역시 유성구 하기동")
                .code("33333")
                .information("대전광역시 유성구 하기동 롯데마트 옆")
                .build();
    }
}
