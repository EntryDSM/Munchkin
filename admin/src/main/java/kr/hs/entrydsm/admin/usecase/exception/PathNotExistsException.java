package kr.hs.entrydsm.admin.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class PathNotExistsException extends MunchkinException {
    public PathNotExistsException() {
        super(ErrorCode.IMAGE_PATH_NOT_FOUND);
    }
}
