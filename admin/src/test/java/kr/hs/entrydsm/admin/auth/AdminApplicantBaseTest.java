package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.usecase.dto.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AdminApplicantBaseTest {

    protected static final Applicant APPLICANT = Applicant.builder()
            .examCode("12036") //수험번호
            .receiptCode(123456L) //접수 번호
            .applicationType("COMMON") //전형 유형 COMMON, MEISTER, SOCIAL
            .isDaejeon(false)
            .name("리슴뮴") //이름
            .birthDate(LocalDate.of(2000,11,12)) //생년월일
            .address("서울 중구 한강대로 405 서울역") //주소
            .detailAddress(null)
            .telephoneNumber("010-0000-0000") //학생 전화번호
            .educationalStatus("PROSPECTIVE_GRADUATE") //학력구분 PROSPECTIVE_GRADUATE, GRADUATE, QUALIFICATION_EXAM
            .studyPlan("학업 계획서") //학업 계획서
            .selfIntroduce("자기 소개서") //자기소개서
            .parentTel("010-8888-8888") //보호자 전화번호
            .homeTel("02-000-0000")
            .schoolTel("02-031-031")
            .isSubmit(true)
            .isPrintedArrived(false)
            .email("test@test.com")
            .averageScore(BigDecimal.valueOf(80.95))
            .conversionScore(BigDecimal.valueOf(143.5))
            .dayAbsenceCount(0)
            .earlyLeaveCount(0)
            .schoolName("이상해지는중")
            .latenessCount(0)
            .volunteerTime(100)
            .lectureAbsenceCount(0)
            .isGraduated(false)
            .studyPlan("누가 dto를 이렇게 뭉텅그려 만든거죠?")
            .selfIntroduce("저 테스트 못하겠어요 살려주세요 뭘 넣었는지도 이제 모르겠고 그냥 다 모르겠어요")
            .build();

    protected static final Evaluation EVALUATION = Evaluation.builder()
            .volunteerTime(100)
            .conversionScore(BigDecimal.valueOf(145.6))
            .dayAbsenceCount(0)
            .lectureAbsenceCount(0)
            .earlyLeaveCount(0)
            .latenessCount(0)
            .selfIntroduce("오마이갓")
            .studyPlan("아니 이렇게 만드는게 맞아요?")
            .averageScore(BigDecimal.valueOf(76.5))
            .build();

    protected static final PersonalData PERSONAL_DATA = PersonalData.builder()
            .name("내이름")
            .email("test@test.com")
            .birthDate(LocalDate.parse("2000-04-04"))
            .schoolName("상주중학교")
            .isGraduated(true)
            .educationalStatus("GRADUATE")
            .applicationType("COMMON")
            .address("서울 송파구 올림픽로 300")
            .telephoneNumber("010-0505-0905")
            .parentTel("010-0505-0505")
            .schoolTel("02-0202-0502")
            .homeTel("031-0505-0909")
            .build();

    protected static final SpecialScore SOCIAL_SCORE = SpecialScore.builder()
            .score_20(1)
            .score21_30(3)
            .score31_40(5)
            .score41_50(9)
            .score51_60(6)
            .score61_70(8)
            .score71_80(7)
            .score81_90(10)
            .build();

    protected static final CommonScore COMMON_SCORE = CommonScore.builder()
            .score_80(1)
            .score81_90(0)
            .score91_100(5)
            .score101_110(1)
            .score111_120(2)
            .score121_130(6)
            .score131_140(7)
            .score141_150(9)
            .build();

    protected static final SpecialScore MEISTER_SCORE = SpecialScore.builder()
            .score_20(1)
            .score21_30(3)
            .score31_40(0)
            .score41_50(5)
            .score51_60(9)
            .score61_70(9)
            .score71_80(7)
            .score81_90(9)
            .build();

    protected final ApplicationStatus APPLICATION_STATUS = ApplicationStatus.builder()
            .commonScore(commonScore)
            .meisterScore(meisterScore)
            .specialScore(specialScore)
            .build();

    protected static final SaveExamCodeUserResponse SAVE_EXAM_CODE_USER_RESPONSE = SaveExamCodeUserResponse.builder()
            .applicationType("MEISTER")
            .isDaejeon(true)
            .examCode("123456")
            .address("경상북도 상주시 경상대로 314")
            .receiptCode(123456)
            .build();

    private static List<BigDecimal> commonScore = Arrays.asList(new BigDecimal[]{BigDecimal.valueOf(123.5), BigDecimal.valueOf(128.545)});
    private static List<BigDecimal> meisterScore = Arrays.asList(new BigDecimal[]{BigDecimal.valueOf(121.2), BigDecimal.valueOf(135.6)});
    private static List<BigDecimal> specialScore = Arrays.asList(new BigDecimal[]{BigDecimal.valueOf(122.5), BigDecimal.valueOf(127.545)});

}