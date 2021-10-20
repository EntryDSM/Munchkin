package kr.hs.entrydsm.admin.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class InvalidKeywordException extends MunchkinException {
    public InvalidKeywordException() {
        super(ErrorCode.INVALID_KEYWORD);
    }
}
