package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.usecase.applicant.ApplicantService;
import kr.hs.entrydsm.admin.usecase.applicant.ApplicantServiceManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@DisplayName("admin-applicant")
@SpringBootTest(classes = ApplicantServiceManager.class)
public class AdminApplicantTest extends AdminApplicantBaseTest {

    @MockBean
    ApplicantService applicantService;


    @Test
    public void changeIsPrintedArrived() {
        applicantService.changeIsPrintedArrived(APPLICANT.getReceiptCode(), true);
    }

    @Test
    public void saveExamCode() throws Exception {
        applicantService.saveExamCode();
    }

}
