package kr.hs.entrydsm.user.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class StatusNotFoundException extends MunchkinException {

    public StatusNotFoundException() {
        super(ErrorCode.STATUS_NOT_FOUND);
    }
}
