package kr.hs.entrydsm.application.presenter.web;

import kr.hs.entrydsm.application.usecase.ApplicationProcessing;
import kr.hs.entrydsm.application.usecase.dto.application.Information;
import kr.hs.entrydsm.application.usecase.dto.application.request.ApplicationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.request.GraduatedInformationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.response.ApplicationResponse;
import kr.hs.entrydsm.application.usecase.dto.application.response.GraduatedInformationResponse;
import kr.hs.entrydsm.common.context.auth.token.JWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Published
@JWTRequired
@RestController
@RequiredArgsConstructor
@RequestMapping("/application/users")
public class ApplicationUserController {

    private final ApplicationProcessing applicationProcessing;

    @PatchMapping("/type")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void selectType(@RequestBody @Valid ApplicationRequest application){
        applicationProcessing.writeApplicationType(application);
    }

    @GetMapping("/type")
    public ApplicationResponse getType(){
        return applicationProcessing.getApplicationType();
    }

    @PatchMapping("/graduation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void insertGraduatedInfo(@RequestBody @Valid GraduatedInformationRequest information){
        applicationProcessing.writeGraduatedInformation(information);
    }

    @GetMapping("/graduation")
    public GraduatedInformationResponse getGraduatedInfo() {
        return applicationProcessing.getGraduatedInformation();
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void insertGedInfo(@RequestBody @Valid Information information) {
        applicationProcessing.writeInformation(information);
    }

    @GetMapping
    public Information getGedInfo() {
        return applicationProcessing.getInformation();
    }

    @PostMapping("/photo")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadPhoto(@RequestPart(name = "file") @Nullable MultipartFile multipartFile) {
        return applicationProcessing.uploadPhoto(multipartFile);
    }

}
