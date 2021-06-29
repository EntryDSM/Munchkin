package kr.hs.entrydsm.application.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class EducationalStatusNotFoundException extends MunchkinException {
    public EducationalStatusNotFoundException() {
        super(ErrorCode.EDUCATIONAL_STATUS_NOT_FOUND);
    }
}
