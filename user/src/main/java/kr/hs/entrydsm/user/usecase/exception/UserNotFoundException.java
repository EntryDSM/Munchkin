package kr.hs.entrydsm.user.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class UserNotFoundException extends MunchkinException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
