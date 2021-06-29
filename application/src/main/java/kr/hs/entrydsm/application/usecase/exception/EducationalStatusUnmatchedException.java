package kr.hs.entrydsm.application.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class EducationalStatusUnmatchedException extends MunchkinException {
    public EducationalStatusUnmatchedException() {
        super(ErrorCode.EDUCATIONAL_STATUS_UNMATCHED);
    }
}
