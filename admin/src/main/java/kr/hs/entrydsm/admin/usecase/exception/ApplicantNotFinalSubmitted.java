package kr.hs.entrydsm.admin.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class ApplicantNotFinalSubmitted extends MunchkinException {
    public ApplicantNotFinalSubmitted() {
        super(ErrorCode.NOT_FINAL_SUBMITTED);
    }
}
