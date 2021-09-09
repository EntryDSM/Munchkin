package kr.hs.entrydsm.user.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class EmptyContentException extends MunchkinException {

    public EmptyContentException() {
        super(ErrorCode.EMPTY_CONTENT);
    }

}
