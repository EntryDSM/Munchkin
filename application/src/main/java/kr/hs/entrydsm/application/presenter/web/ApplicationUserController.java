package kr.hs.entrydsm.application.presenter.web;

import kr.hs.entrydsm.application.usecase.ApplicationProcessing;
import kr.hs.entrydsm.application.usecase.dto.Application;
import kr.hs.entrydsm.application.usecase.dto.Information;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Published
@RestController
@RequiredArgsConstructor
@RequestMapping("/application/users")
public class ApplicationUserController {

    private final ApplicationProcessing applicationProcessing;

    @PatchMapping("/type")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void selectType(@RequestBody Application application){
        applicationProcessing.writeApplicationType(1L, application);
    }

    @GetMapping("/type")
    public Application getType(){
        return applicationProcessing.getApplicationType(1L);
    }

    @PatchMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void insertInfo(@RequestBody Information information){
        applicationProcessing.writeInformation(1L, information);
    }

    @GetMapping("/")
    public Information getInfo() throws IOException {
        return applicationProcessing.getInformation(1L);
    }

    @PostMapping("/photo")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadPhoto(@RequestPart @Nullable MultipartFile multipartFile) throws IOException {
        return applicationProcessing.uploadPhoto(multipartFile);
    }

}
