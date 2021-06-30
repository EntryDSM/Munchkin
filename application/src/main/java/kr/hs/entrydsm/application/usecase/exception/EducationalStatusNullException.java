package kr.hs.entrydsm.application.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class EducationalStatusNullException extends MunchkinException {

    public EducationalStatusNullException() {
        super(ErrorCode.EDUCATIONAL_STATUS_NULL);
    }
}
