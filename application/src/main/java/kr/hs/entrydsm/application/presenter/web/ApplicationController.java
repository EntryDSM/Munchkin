package kr.hs.entrydsm.application.presenter.web;

import kr.hs.entrydsm.application.usecase.ApplicationProcessing;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@Published
@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationProcessing applicationProcessing;

    @PatchMapping("/intro")
    public void updateIntro(@RequestBody @NotBlank String content) {
        applicationProcessing.writeSelfIntroduce(1L, content);
    }

    @PatchMapping("/study-plan")
    public void updateStudyPlan(@RequestBody @NotBlank String content) {
        applicationProcessing.writeStudyPlan(1L, content);
    }

    @GetMapping("/intro")
    public String getIntro() {
        return applicationProcessing.getSelfIntroduce(1L);
    }

    @GetMapping("/study-plan")
    public String getStudyPlan() {
        return applicationProcessing.getStudyPlan(1L);
    }
}
