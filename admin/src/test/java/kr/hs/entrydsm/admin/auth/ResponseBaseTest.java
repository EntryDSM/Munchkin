package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.usecase.dto.account.AccessTokenResponse;
import kr.hs.entrydsm.admin.usecase.dto.account.TokenResponse;
import kr.hs.entrydsm.admin.usecase.dto.applicant.*;
import kr.hs.entrydsm.admin.usecase.dto.schedule.ScheduleResponse;
import kr.hs.entrydsm.admin.usecase.dto.tmap.RouteGuidanceResponse;

public abstract class ResponseBaseTest {

    protected static final AccessTokenResponse ACCESS_TOKEN_RESPONSE = new AccessTokenResponse("123456");

    protected static final ApplicantDetailResponse APPLICANT_DETAIL_RESPONSE = ApplicantDetailResponse.builder()
            .evaluation(null)
            .personalData(null)
            .status(null)
            .build();

    protected static final ApplicantsInformationResponse APPLICANTS_INFORMATION_RESPONSE = ApplicantsInformationResponse.builder()
            .receiptCode(123456L)
            .name("홍길동")
            .isDaejeon(true)
            .applicationType("COMMON")
            .isPrintedArrived(true)
            .isSubmit(true)
            .build();

    protected static final ApplicantsResponse APPLICANTS_RESPONSE = ApplicantsResponse.builder()
            .applicantsInformationResponses(null)
            .build();

    protected static final PageResponse PAGE_RESPONSE = new PageResponse(1, 2);

    protected static final ApplicantsResponse APPLICANTS_RESPONSE2 = new ApplicantsResponse(null);

    protected static final ReceiptStatusResponse RECEIPT_STATUS_RESPONSE = ReceiptStatusResponse.builder()
            .totalApplicantCount(0)
            .totalCompetitionRate(0.0)
            .commonScore(null)
            .meisterScore(null)
            .socialScore(null)
            .commonCount(0)
            .commonCompetitionRate(0.0)
            .meisterCount(0)
            .meisterCompetitionRate(0.0)
            .socialCount(0)
            .socialCompetitionRate(0.0)
            .build();

    protected static final RouteGuidanceResponse ROUTE_GUIDANCE_RESPONSE = RouteGuidanceResponse.builder()
            .type(null)
            .properties(null)
            .build();

    protected static final ScheduleResponse SCHEDULE_RESPONSE = ScheduleResponse.builder()
            .schedules(null)
            .currentStatus("null")
            .build();

    protected static final TokenResponse TOKEN_RESPONSE = new TokenResponse("asdf","asdf");

}
