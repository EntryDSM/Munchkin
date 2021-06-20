package kr.hs.entrydsm.user.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class InvalidEnumConstantException extends MunchkinException {
    public InvalidEnumConstantException() {
        super(ErrorCode.INVALID_ENUM_CONSTANT);
    }
}
