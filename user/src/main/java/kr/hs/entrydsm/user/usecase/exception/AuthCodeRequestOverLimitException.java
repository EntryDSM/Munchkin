package kr.hs.entrydsm.user.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class AuthCodeRequestOverLimitException extends MunchkinException {
    public AuthCodeRequestOverLimitException() {
        super(ErrorCode.AUTH_CODE_REQUEST_OVER_LIMIT);
    }
}
