package kr.hs.entrydsm.score.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class GradeNotFoundException extends MunchkinException {
    public GradeNotFoundException() {
        super(ErrorCode.GRADE_NOT_FOUND);
    }
}
