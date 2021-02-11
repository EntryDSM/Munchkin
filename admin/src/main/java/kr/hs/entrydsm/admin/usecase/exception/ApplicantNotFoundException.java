package kr.hs.entrydsm.admin.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class ApplicantNotFoundException extends MunchkinException {
    public ApplicantNotFoundException() {
        super(ErrorCode.APPLICATION_NOT_FOUND);
    }
}
