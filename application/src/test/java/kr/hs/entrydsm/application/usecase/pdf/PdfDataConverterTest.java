package kr.hs.entrydsm.application.usecase.pdf;

import kr.hs.entrydsm.application.builder.ApplicantBuilder;
import kr.hs.entrydsm.application.builder.CalculatedScoreBuilder;
import kr.hs.entrydsm.application.builder.GraduationApplicationBuilder;
import kr.hs.entrydsm.application.builder.QualificationExamApplicationBuilder;
import kr.hs.entrydsm.application.entity.*;
import kr.hs.entrydsm.application.usecase.dto.CalculatedScore;
import kr.hs.entrydsm.application.usecase.dto.PdfData;
import kr.hs.entrydsm.application.usecase.image.ImageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PdfDataConverterTest {

    @InjectMocks
    private PdfDataConverter pdfDataConverter;

    @Mock
    private GraduationApplicationRepository graduationApplicationRepository;

    @Mock
    private QualificationExamApplicationRepository qualificationExamApplicationRepository;

    @Mock
    private ImageService imageService;

    @Test
    public void convertPdfData() throws IOException {
        LocalDateTime now = LocalDateTime.now();

        final String checkedBallotBox = "☑";
        final String emptyBallotBox = "☐";

        Applicant applicant = ApplicantBuilder.build();
        CalculatedScore calculatedScore = CalculatedScoreBuilder.build();

        GraduationApplication graduationApplication = GraduationApplicationBuilder.build();
        QualificationExamApplication qualificationExamApplication = QualificationExamApplicationBuilder.build();

        given(graduationApplicationRepository.findByReceiptCode(applicant.getReceiptCode()))
                .willReturn(Optional.of(graduationApplication));
        given(qualificationExamApplicationRepository.findByReceiptCode(applicant.getReceiptCode()))
                .willReturn(Optional.of(qualificationExamApplication));
        given(imageService.getObject(applicant.getPhotoFileName())).willReturn(new byte[]{});

        PdfData result = pdfDataConverter.applicationToInfo(applicant, calculatedScore);

        assertThat(result.getReceiptCode()).isEqualTo(applicant.getReceiptCode());
        assertThat(result.getEntranceYear()).isEqualTo(String.valueOf(LocalDate.now().plusYears(1).getYear()));
        assertThat(result.getUserName()).isEqualTo(applicant.getName());
        assertThat(result.getIsMale()).isEqualTo(checkedBallotBox);
        assertThat(result.getIsFemale()).isEqualTo(emptyBallotBox);
        assertThat(result.getAddress()).isEqualTo(applicant.getAddress());
        assertThat(result.getBirthday()).isEqualTo("2003년 08월 05일");
        assertThat(result.getGender()).isEqualTo("남");
        assertThat(result.getSchoolCode()).isEqualTo(graduationApplication.getSchoolCode());
        assertThat(result.getSchoolClass()).isEqualTo(graduationApplication.getSchoolClass());
        assertThat(result.getSchoolName()).isEqualTo(graduationApplication.getSchoolName());
        assertThat(result.getSchoolTel()).isEqualTo(graduationApplication.getSchoolTel());
        assertThat(result.getApplicantTel()).isEqualTo(applicant.getTelephoneNumber());
        assertThat(result.getParentTel()).isEqualTo(applicant.getParentTel());
        assertThat(result.getHomeTel()).isEqualTo(applicant.getHomeTel());
        assertThat(result.getQualificationExamPassedYear()).isEqualTo("20__");
        assertThat(result.getQualificationExamPassedMonth()).isEqualTo("__");
        assertThat(result.getGraduateYear()).isEqualTo("20__");
        assertThat(result.getGraduateMonth()).isEqualTo("__");
        assertThat(result.getProspectiveGraduateMonth()).isEqualTo("2");
        assertThat(result.getIsQualificationExam()).isEqualTo(emptyBallotBox);
        assertThat(result.getIsGraduate()).isEqualTo(emptyBallotBox);
        assertThat(result.getIsProspectiveGraduate()).isEqualTo(checkedBallotBox);
        assertThat(result.getIsDaejeon()).isEqualTo(checkedBallotBox);
        assertThat(result.getIsNotDaejeon()).isEqualTo(emptyBallotBox);
        assertThat(result.getIsBasicLiving()).isEqualTo(emptyBallotBox);
        assertThat(result.getIsFromNorth()).isEqualTo(emptyBallotBox);
        assertThat(result.getIsLowestIncome()).isEqualTo(emptyBallotBox);
        assertThat(result.getIsMulticultural()).isEqualTo(checkedBallotBox);
        assertThat(result.getIsOneParent()).isEqualTo(emptyBallotBox);
        assertThat(result.getIsTeenHouseholder()).isEqualTo(emptyBallotBox);
        assertThat(result.getIsPrivilegedAdmission()).isEqualTo(emptyBallotBox);
        assertThat(result.getIsNationalMerit()).isEqualTo(emptyBallotBox);
        assertThat(result.getIsCommon()).isEqualTo(emptyBallotBox);
        assertThat(result.getIsMeister()).isEqualTo(emptyBallotBox);
        assertThat(result.getIsSocialMerit()).isEqualTo(checkedBallotBox);
        assertThat(result.getConversionScore1st()).isEqualTo("1");
        assertThat(result.getConversionScore2nd()).isEqualTo("1");
        assertThat(result.getConversionScore3rd()).isEqualTo("1");
        assertThat(result.getConversionScore()).isEqualTo("1");
        assertThat(result.getAttendanceScore()).isEqualTo("1");
        assertThat(result.getVolunteerScore()).isEqualTo("1");
        assertThat(result.getFinalScore()).isEqualTo("1");
        assertThat(result.getYear()).isEqualTo(String.valueOf(now.getYear()));
        assertThat(result.getMonth()).isEqualTo(String.valueOf(now.getMonthValue()));
        assertThat(result.getDay()).isEqualTo(String.valueOf(now.getDayOfMonth()));
        assertThat(result.getSelfIntroduction()).isEqualTo(applicant.getSelfIntroduce());
        assertThat(result.getStudyPlan()).isEqualTo(applicant.getStudyPlan());
    }
}
