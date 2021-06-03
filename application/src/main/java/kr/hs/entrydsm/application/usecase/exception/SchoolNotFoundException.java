package kr.hs.entrydsm.application.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class SchoolNotFoundException extends MunchkinException {
    public SchoolNotFoundException(){
        super(ErrorCode.SCHOOL_NOT_FOUND);
    }
}
