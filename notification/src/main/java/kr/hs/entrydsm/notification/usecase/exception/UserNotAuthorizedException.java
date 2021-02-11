package kr.hs.entrydsm.notification.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class UserNotAuthorizedException extends MunchkinException {
    public UserNotAuthorizedException() {
        super(ErrorCode.NOT_AUTHORIZED);
    }
}
