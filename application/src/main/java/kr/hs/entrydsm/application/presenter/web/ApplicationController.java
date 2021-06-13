package kr.hs.entrydsm.application.presenter.web;

import kr.hs.entrydsm.application.usecase.ApplicationProcessing;
import kr.hs.entrydsm.application.usecase.dto.EtcScore;
import kr.hs.entrydsm.application.usecase.dto.SubjectScore;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Published
@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationProcessing applicationProcessing;

    @PatchMapping("/intro")
    public void updateIntro(@RequestBody @NotBlank String content) {
        applicationProcessing.writeSelfIntroduce(content);
    }

    @PatchMapping("/study-plan")
    public void updateStudyPlan(@RequestBody @NotBlank String content) {
        applicationProcessing.writeStudyPlan(content);
    }

    @GetMapping("/intro")
    public String getIntro() {
        return applicationProcessing.getSelfIntroduce();
    }

    @GetMapping("/study-plan")
    public String getStudyPlan() {
        return applicationProcessing.getStudyPlan();
    }

    @PatchMapping("/score/subject")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSubjectScore(@RequestBody @Valid SubjectScore score) {
        applicationProcessing.updateSubjectScore(score);
    }

    @PatchMapping("/score/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEtcScore(@RequestBody @Valid EtcScore score) {
        applicationProcessing.updateEtcScore(score);
    }

}
