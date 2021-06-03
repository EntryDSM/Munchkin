package kr.hs.entrydsm.application.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class FileIsEmptyException extends MunchkinException {
    public FileIsEmptyException(){
        super(ErrorCode.FILE_IS_EMPTY);
    }
}
