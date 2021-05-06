package kr.hs.entrydsm.application.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class FinalSubmitRequiredException extends MunchkinException {

    public FinalSubmitRequiredException() {
        super(ErrorCode.FINAL_SUBMIT_REQUIRED);
    }
}
