package kr.hs.entrydsm.admin.auth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("admin-response")
public class ResponseTest extends ResponseBaseTest {

    @Test
    public void access_token() {
        assertNotNull(ACCESS_TOKEN_RESPONSE);
    }

    @Test
    public void applicant_detail_response() {
        assertNull(APPLICANT_DETAIL_RESPONSE.getEvaluation());
        assertNull(APPLICANT_DETAIL_RESPONSE.getPersonalData());
        assertNull(APPLICANT_DETAIL_RESPONSE.getStatus());
    }

    @Test
    public void applicants_information_response() {
        assertNotNull(APPLICANTS_INFORMATION_RESPONSE.getReceiptCode());
        assertNotNull(APPLICANTS_INFORMATION_RESPONSE.getName());
        assertNotNull(APPLICANTS_INFORMATION_RESPONSE.getIsDaejeon());
        assertNotNull(APPLICANTS_INFORMATION_RESPONSE.getIsPrintedArrived());
        assertNotNull(APPLICANTS_INFORMATION_RESPONSE.getApplicationType());
        assertNotNull(APPLICANTS_INFORMATION_RESPONSE.getIsSubmit());
    }

    @Test
    public void applicants_response() {
        assertNull(APPLICANTS_RESPONSE.getApplicantsInformationResponses());
        assertNull(APPLICANTS_RESPONSE2.getApplicantsInformationResponses());
    }

    @Test
    public void receipt_status_response() {
        assertTrue(RECEIPT_STATUS_RESPONSE.getTotalApplicantCount()==0);
        assertTrue(RECEIPT_STATUS_RESPONSE.getTotalCompetitionRate() == 0.0);
        assertNull(RECEIPT_STATUS_RESPONSE.getCommonScore());
        assertNull(RECEIPT_STATUS_RESPONSE.getMeisterScore());
        assertNull(RECEIPT_STATUS_RESPONSE.getSocialScore());
    }

    @Test
    public void route_guidance_response() {
        assertNull(ROUTE_GUIDANCE_RESPONSE.getType());
        assertNull(ROUTE_GUIDANCE_RESPONSE.getProperties());
    }

    @Test
    public void schedule_response() {
        assertNull(SCHEDULE_RESPONSE.getSchedules());
        assertTrue(SCHEDULE_RESPONSE.getCurrentStatus().equals("null"));
    }

    @Test
    public void token_response() {
        assertTrue(TOKEN_RESPONSE.getAccessToken().equals("asdf"));
        assertTrue(TOKEN_RESPONSE.getRefreshToken().equals("asdf"));
    }

}
