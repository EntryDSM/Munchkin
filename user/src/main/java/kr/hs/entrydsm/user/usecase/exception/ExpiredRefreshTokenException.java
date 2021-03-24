package kr.hs.entrydsm.user.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class ExpiredRefreshTokenException extends MunchkinException {
    public ExpiredRefreshTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
