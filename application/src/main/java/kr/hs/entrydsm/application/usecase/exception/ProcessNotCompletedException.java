package kr.hs.entrydsm.application.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class ProcessNotCompletedException extends MunchkinException {

    public ProcessNotCompletedException() {
        super(ErrorCode.PROCESS_NOT_COMPLETED);
    }
}
