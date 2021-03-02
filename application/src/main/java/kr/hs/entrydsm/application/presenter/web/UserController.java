package kr.hs.entrydsm.application.presenter.web;

import kr.hs.entrydsm.application.domain.entity.Applicant;
import kr.hs.entrydsm.application.infrastructure.database.ApplicationRepositoryManager;
import kr.hs.entrydsm.application.infrastructure.database.SchoolRepositoryManager;
import kr.hs.entrydsm.application.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.application.usecase.dto.Application;
import kr.hs.entrydsm.application.usecase.dto.Information;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application/users")
public class UserController {

    private final ApplicantRepository applicantRepository;

    @PatchMapping("/type")
    public void selectType(@RequestBody Application application){
        applicantRepository.writeApplicationType(1L, application);
    }

    @GetMapping("/type")
    public Application getType(){
        return applicantRepository.getApplicationType(1L);
    }

    @PatchMapping("/")
    public void insertInfo(@RequestBody Information information){
        applicantRepository.writeInformation(1L, information);
    }

    @GetMapping("/")
    public Information getInfo(){
        return applicantRepository.getInformation(1L);
    }

}
