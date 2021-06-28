package kr.hs.entrydsm.application.builder;

import kr.hs.entrydsm.application.entity.Applicant;
import kr.hs.entrydsm.application.entity.ApplicationRemark;
import kr.hs.entrydsm.application.entity.ApplicationType;
import kr.hs.entrydsm.application.entity.EducationalStatus;

import java.time.LocalDate;

public class ApplicantBuilder {

    public static Applicant build() {
        return Applicant.builder()
                .receiptCode(1)
                .telephoneNumber("01000000000")
                .applicationType(ApplicationType.SOCIAL)
                .applicationRemark(ApplicationRemark.MULTICULTURAL)
                .educationalStatus(EducationalStatus.PROSPECTIVE_GRADUATE)
                .isDaejeon(true)
                .name("김대덕")
                .sex("MALE")
                .birthday(LocalDate.of(2003, 8, 5))
                .parentName("김부모")
                .parentTel("01011111111")
                .address("대전광역시 유성구")
                .postCode("33333")
                .photoFileName("김대덕.jpg")
                .homeTel("0420000000")
                .selfIntroduce("안녕하세요.")
                .studyPlan("공부 어쩌구 저쩌구")
                .build();
    }
}
