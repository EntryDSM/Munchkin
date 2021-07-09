package kr.hs.entrydsm.score.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class GradeOrScoreNotFoundException extends MunchkinException {
    public GradeOrScoreNotFoundException() {
        super(ErrorCode.GRADE_OR_SCORE_NOT_FOUND);
    }
}
