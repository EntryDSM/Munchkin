package kr.hs.entrydsm.application.presenter.web;

import kr.hs.entrydsm.application.usecase.ApplicationProcessing;
import kr.hs.entrydsm.application.usecase.dto.application.request.ApplicationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.response.ApplicationResponse;
import kr.hs.entrydsm.application.usecase.dto.application.request.InformationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.response.InformationResponse;
import kr.hs.entrydsm.common.context.auth.token.JWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

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

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void insertInfo(@RequestBody @Valid InformationRequest information){
        applicationProcessing.writeInformation(information);
    }

    @GetMapping
    public InformationResponse getInfo() throws IOException {
        return applicationProcessing.getInformation();
    }

    @PostMapping("/photo")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadPhoto(@RequestPart @Nullable MultipartFile multipartFile) throws IOException {
        return applicationProcessing.uploadPhoto(multipartFile);
    }

}
