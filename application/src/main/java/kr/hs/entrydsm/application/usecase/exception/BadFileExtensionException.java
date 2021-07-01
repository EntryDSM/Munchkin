package kr.hs.entrydsm.application.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class BadFileExtensionException extends MunchkinException {

    public BadFileExtensionException() {
        super(ErrorCode.BAD_FILE_EXTENSION);
    }

}
