package kr.hs.entrydsm.admin.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class AlreadyExistAdminIdException extends MunchkinException {
    public AlreadyExistAdminIdException() {
        super(ErrorCode.ALREADY_EXIST_ADMIN_ID);
    }
}
