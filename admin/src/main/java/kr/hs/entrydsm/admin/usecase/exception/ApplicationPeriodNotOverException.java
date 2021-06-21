package kr.hs.entrydsm.admin.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class ApplicationPeriodNotOverException extends MunchkinException {
    public ApplicationPeriodNotOverException() {
        super(ErrorCode.APPLICATION_PERIOD_NOT_OVER);
    }
}
