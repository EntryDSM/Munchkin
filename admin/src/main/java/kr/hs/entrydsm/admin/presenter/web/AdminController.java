package kr.hs.entrydsm.admin.presenter.web;

import kr.hs.entrydsm.admin.domain.entity.Applicant;
import kr.hs.entrydsm.admin.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Published
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final ApplicantRepository applicantRepository;

    @GetMapping("/applicants")
    public List<Applicant> getApplicants() {
        return applicantRepository.findAll();
    }

}
