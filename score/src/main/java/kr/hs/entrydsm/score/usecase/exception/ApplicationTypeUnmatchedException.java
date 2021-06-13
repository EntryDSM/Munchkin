package kr.hs.entrydsm.score.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class ApplicationTypeUnmatchedException extends MunchkinException {
    public ApplicationTypeUnmatchedException() { super(ErrorCode.APPLICATION_TYPE_UNMATCHED); }
}
