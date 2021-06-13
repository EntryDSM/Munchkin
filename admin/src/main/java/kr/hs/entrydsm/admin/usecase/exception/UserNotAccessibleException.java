package kr.hs.entrydsm.admin.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class UserNotAccessibleException extends MunchkinException {
    public UserNotAccessibleException() {
        super(ErrorCode.NOT_ACCESSIBLE);
    }
}
