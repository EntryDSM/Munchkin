package kr.hs.entrydsm.application.presenter.web;

import kr.hs.entrydsm.application.domain.entity.Applicant;
import kr.hs.entrydsm.application.infrastructure.database.ApplicationRepositoryManager;
import kr.hs.entrydsm.application.infrastructure.database.SchoolRepositoryManager;
import kr.hs.entrydsm.application.usecase.dto.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application/users")
public class UserController {

    private final SchoolRepositoryManager schoolRepositoryManager;
    private final ApplicationRepositoryManager applicationRepositoryManager;

    @PatchMapping("/type")
    public void selectType(@RequestBody Application application){
        applicationRepositoryManager.save(application);
    }

    @GetMapping("/type")
    public Applicant getType(){
        return applicationRepositoryManager.findByReceiptCode(1L);
    }

    @PatchMapping("/")
    public void insertInfo(@RequestBody Applicant applicant){
        applicationRepositoryManager.save(applicant);
    }

    @GetMapping("/")
    public Applicant getInfo(){
        return applicationRepositoryManager.findByReceiptCode(1L);
    }

}
