package kr.hs.entrydsm.score.presenter.web;

import kr.hs.entrydsm.common.context.auth.token.JWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.score.usecase.ScoreService;
import kr.hs.entrydsm.score.usecase.dto.QueryGraduationResponse;
import kr.hs.entrydsm.score.usecase.dto.QueryQualificationExamResponse;
import kr.hs.entrydsm.score.usecase.dto.UpdateGraduationRequest;
import kr.hs.entrydsm.score.usecase.dto.UpdateQualificationExamRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Published
@JWTRequired
@RestController
@RequestMapping("/score")
public class ScoreController {
    private final ScoreService scoreService;

    @GetMapping("/graduation")
    public QueryGraduationResponse queryGraduation() {
        return scoreService.queryGraduation();
    }

    @GetMapping("/qualification-exam")
    public QueryQualificationExamResponse queryQualification() {
        return scoreService.queryQualificationExam();
    }

    @PatchMapping("/graduation")
    public void updateGraduation(@Valid @RequestBody UpdateGraduationRequest request) {
        scoreService.updateGraduation(request);
    }

    @PatchMapping("/qualification-exam")
    public void updateQualificationExam(@Valid @RequestBody UpdateQualificationExamRequest request) {
        scoreService.updateQualificationExam(request);
    }
}
