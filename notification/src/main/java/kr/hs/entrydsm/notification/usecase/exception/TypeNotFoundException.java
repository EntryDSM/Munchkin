package kr.hs.entrydsm.notification.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class TypeNotFoundException extends MunchkinException {
    public TypeNotFoundException() {
        super(ErrorCode.TYPE_NOT_FOUND);
    }
}
