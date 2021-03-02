package kr.hs.entrydsm.application.presenter.web;

import kr.hs.entrydsm.application.entity.Applicant;
import kr.hs.entrydsm.application.infrastructure.database.ApplicationRepositoryManager;
import kr.hs.entrydsm.application.infrastructure.database.SchoolRepositoryManager;
import kr.hs.entrydsm.application.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.application.usecase.ApplicationProcessing;
import kr.hs.entrydsm.application.usecase.dto.Application;
import kr.hs.entrydsm.application.usecase.dto.Information;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application/users")
public class UserController {

    private final ApplicationProcessing applicationProcessing;

    @PatchMapping("/type")
    public void selectType(@RequestBody Application application){
        applicationProcessing.writeApplicationType(1L, application);
    }

    @GetMapping("/type")
    public Application getType(){
        return applicationProcessing.getApplicationType(1L);
    }

    @PatchMapping("/")
    public void insertInfo(@RequestBody Information information){
        applicationProcessing.writeInformation(1L, information);
    }

    @GetMapping("/")
    public Information getInfo(){
        return applicationProcessing.getInformation(1L);
    }

}
