package kr.hs.entrydsm.application.presenter.web;

import kr.hs.entrydsm.application.usecase.ApplicationProcessing;
import kr.hs.entrydsm.application.usecase.dto.EtcScore;
import kr.hs.entrydsm.application.usecase.dto.GedScore;
import kr.hs.entrydsm.application.usecase.dto.SubjectScore;
import kr.hs.entrydsm.application.usecase.dto.UpdateDocsRequest;
import kr.hs.entrydsm.common.context.auth.token.JWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Published
@JWTRequired
@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationProcessing applicationProcessing;

    @PatchMapping("/intro")
    public void updateIntro(@RequestBody @Valid UpdateDocsRequest dto) {
        applicationProcessing.writeSelfIntroduce(dto.getContent());
    }

    @PatchMapping("/study-plan")
    public void updateStudyPlan(@RequestBody @Valid UpdateDocsRequest dto) {
        applicationProcessing.writeStudyPlan(dto.getContent());
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

    @PatchMapping("/score/etc")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEtcScore(@RequestBody @Valid EtcScore score) {
        applicationProcessing.updateEtcScore(score);
    }

    @PatchMapping("/score/ged")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGedScore(@RequestBody @Valid GedScore score) {
        applicationProcessing.updateGedScore(score);
    }

    @GetMapping("/score/subject")
    public SubjectScore getSubjectScore() {
        return applicationProcessing.getSubjectScore();
    }

    @GetMapping("/score/etc")
    public EtcScore getEtcScore() {
        return applicationProcessing.getEtcScore();
    }

    @GetMapping("/score/ged")
    public GedScore getGedScore() {
        return applicationProcessing.getGedScore();
    }

}
