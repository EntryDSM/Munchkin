package kr.hs.entrydsm.application.presenter.web;

import kr.hs.entrydsm.application.usecase.ApplicationProcessing;
import kr.hs.entrydsm.application.usecase.dto.application.request.UpdateDocsRequest;
import kr.hs.entrydsm.common.context.auth.token.JWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Published
@JWTRequired
@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationProcessing applicationProcessing;

    @PatchMapping("/intro")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateIntro(@RequestBody @Valid UpdateDocsRequest dto) {
        applicationProcessing.writeSelfIntroduce(dto.getContent());
    }

    @PatchMapping("/study-plan")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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

//    @PatchMapping("/score/subject")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateSubjectScore(@RequestBody @Valid SubjectScoreRequest score) {
//        applicationProcessing.updateSubjectScore(score);
//    }
//
//    @PatchMapping("/score/etc")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateEtcScore(@RequestBody @Valid EtcScoreRequest score) {
//        applicationProcessing.updateEtcScore(score);
//    }
//
//    @PatchMapping("/score/ged")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateGedScore(@RequestBody @Valid GedScoreRequest score) {
//        applicationProcessing.updateGedScore(score);
//    }
//
//    @GetMapping("/score/subject")
//    public SubjectScoreResponse getSubjectScore() {
//        return applicationProcessing.getSubjectScore();
//    }
//
//    @GetMapping("/score/etc")
//    public EtcScoreResponse getEtcScore() {
//        return applicationProcessing.getEtcScore();
//    }
//
//    @GetMapping("/score/ged")
//    public GedScoreResponse getGedScore() {
//        return applicationProcessing.getGedScore();
//    }
//
//    @GetMapping("/score")
//    public TotalScoreResponse getScore() {
//        return applicationProcessing.getScore();
//    }

}
