package kr.hs.entrydsm.user.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class AuthCodeAlreadyVerifiedException extends MunchkinException {
    public AuthCodeAlreadyVerifiedException() {
        super(ErrorCode.AUTH_CODE_ALREADY_VERIFIED);
    }
}
