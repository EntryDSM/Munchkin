package kr.hs.entrydsm.user.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class InvalidTokenException extends MunchkinException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
