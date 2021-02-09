package kr.hs.entrydsm.admin.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class AdminNotFoundException extends MunchkinException {
    public AdminNotFoundException() {
        super(ErrorCode.ADMIN_NOT_FOUND);
    }
}
