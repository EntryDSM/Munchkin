package kr.hs.entrydsm.admin.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class PasswordNotValidException extends MunchkinException {
    public PasswordNotValidException() {
        super(ErrorCode.INVALID_ADMIN_PASSWORD);
    }
}
