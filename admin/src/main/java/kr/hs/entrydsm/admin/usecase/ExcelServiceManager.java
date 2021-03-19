package kr.hs.entrydsm.admin.usecase;

import kr.hs.entrydsm.admin.entity.Applicant;
import kr.hs.entrydsm.admin.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.admin.presenter.excel.AdmissionTicket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ExcelServiceManager implements ExcelService {

    private final ApplicantRepository applicantRepository;

    @Override
    public void createAdmissionTicket(int receiptCode) throws IOException { // 유저 수험표 만들기 "수험번호", "성명", "출신 중학교", "지역", "전형 유형", "접수 번호"
        Applicant applicant = applicantRepository.getUserInfo(receiptCode);

        String examCode = applicant.getExamCode();
        String name = applicant.getName();
        String middleSchool = applicant.getSchoolName();
        String area = applicant.isDaejeon()?"대전":"전국";
        String applicationType = applicant.getApplicationType();
        //receiptCode

        //admissionTicket에 넣어주기
        AdmissionTicket admissionTicket = new AdmissionTicket(examCode, name, middleSchool, area, applicationType, String.valueOf(receiptCode));
        admissionTicket.format(0,0);
        admissionTicket.getWorkbook().write(new FileOutputStream("./"+name+" 수험표.xlsx"));
    }

}
