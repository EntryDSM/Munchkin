package kr.hs.entrydsm.application.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class NullGradeExistException extends MunchkinException {

    public NullGradeExistException() {
        super(ErrorCode.NULL_GRADE_EXIST);
    }
}
