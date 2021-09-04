package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.usecase.dto.applicant.*;
import kr.hs.entrydsm.admin.usecase.dto.tmap.RouteGuidanceRequest;
import kr.hs.entrydsm.admin.usecase.dto.score.CommonScoreResponse;
import kr.hs.entrydsm.admin.usecase.dto.score.SpecialScoreResponse;
import kr.hs.entrydsm.admin.usecase.dto.tmap.Coordinate;
import kr.hs.entrydsm.admin.usecase.dto.tmap.Properties;
import kr.hs.entrydsm.admin.usecase.dto.tmap.RouteBody;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AdminApplicantBaseTest {

    protected static final UserInfo APPLICANT = UserInfo.builder()
            .examCode("12036")
            .receiptCode(123456L)
            .applicationType("COMMON")
            .isDaejeon(false)
            .name("리슴뮴")
            .birthDate(LocalDate.of(2000,11,12))
            .address("서울 중구 한강대로 405 서울역")
            .detailAddress(null)
            .telephoneNumber("010-0000-0000")
            .educationalStatus("PROSPECTIVE_GRADUATE")
            .parentTel("010-8888-8888")
            .homeTel("02-000-0000")
            .photoFileName("asdf")
            .isSubmit(true)
            .isPrintedArrived(false)
            .email("test@test.com")
            .selfIntroduce("hello")
            .studyPlan("hello")
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

    protected static final CommonScoreResponse COMMON_SCORE = CommonScoreResponse.builder()
            .score_79(1)
            .score80_92(0)
            .score93_105(5)
            .score106_118(1)
            .score119_131(2)
            .score132_144(6)
            .score145_157(7)
            .score158_170(9)
            .build();

    protected static final SpecialScoreResponse MEISTER_SCORE = SpecialScoreResponse.builder()
            .score_19(1)
            .score20_32(3)
            .score33_45(0)
            .score46_58(5)
            .score59_71(9)
            .score72_84(9)
            .score85_97(7)
            .score98_110(9)
            .build();

    protected final ApplicationStatus APPLICATION_STATUS = ApplicationStatus.builder()
            .commonScores(commonScore)
            .meisterScores(meisterScore)
            .specialScores(specialScore)
            .build();

    protected static final SaveExamCodeUserResponse SAVE_EXAM_CODE_USER_RESPONSE = SaveExamCodeUserResponse.builder()
            .applicationType("MEISTER")
            .isDaejeon(true)
            .examCode("123456")
            .address("경상북도 상주시 경상대로 314")
            .receiptCode(123456)
            .build();

    protected static final Coordinate COORDINATE = Coordinate.builder()
            .lat("뭐들어가야 하지")
            .lon("진짜 뭐 들어가야 하는지 모라요")
            .cityDo("경기도")
            .guGun("기흥구")
            .eupMyun("오잉")
            .legalDong("이건 뭐죠")
            .adminDong("띠용")
            .ri("이런거 업는")
            .bunji("대박")
            .buildingName("빌딩네임")
            .buildingDong("빌딩동")
            .latEntr("다 스트링이네")
            .lonEntr("오마이갓")
            .newRoadName("아잇")
            .newBuildingIndex("지금은")
            .zipcode("1교시")
            .build();

    protected static final NotSubmitApplicantResponse NOT_SUBMIT_APPLICANT = NotSubmitApplicantResponse.builder()
            .applicantTel("010-0000-0000")
            .parentTel("010-0000-0000")
            .homeTel("010-0000-0000")
            .schoolTel("010-0000-0000")
            .build();

    protected static final Properties PROPERTIES = Properties.builder()
            .totalDistance(123.123)
            .totalTime(1)
            .totalFare(2)
            .taxiFare(123)
            .build();

    protected static final RouteBody ROUTE_BODY = RouteBody.builder()
            .endX(1)
            .endY(1)
            .startX(1)
            .startY(1)
            .totalValue(1)
            .build();

    protected static final Status STATUS = Status.builder()
            .isPrintedArrived(true)
            .isSubmit(true)
            .build();

    protected static final RouteGuidanceRequest ROUTE_GUIDANCE_REQUEST = RouteGuidanceRequest.builder()
            .lng(0)
            .lat(0)
            .startX(0)
            .startY(0)
            .totalValue(0)
            .build();

    protected static final Pageable PAGEABLE = new Pageable() {
        @Override
        public int getPageNumber() {
            return 1;
        }

        @Override
        public int getPageSize() {
            return 3;
        }

        @Override
        public long getOffset() {
            return 0;
        }

        @Override
        public Sort getSort() {
            return null;
        }

        @Override
        public Pageable next() {
            return null;
        }

        @Override
        public Pageable previousOrFirst() {
            return null;
        }

        @Override
        public Pageable first() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }
    };

    private static List<BigDecimal> commonScore = Arrays.asList(new BigDecimal[]{BigDecimal.valueOf(123.5), BigDecimal.valueOf(128.545)});
    private static List<BigDecimal> meisterScore = Arrays.asList(new BigDecimal[]{BigDecimal.valueOf(121.2), BigDecimal.valueOf(135.6)});
    private static List<BigDecimal> specialScore = Arrays.asList(new BigDecimal[]{BigDecimal.valueOf(122.5), BigDecimal.valueOf(127.545)});

}
