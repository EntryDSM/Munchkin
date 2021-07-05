package kr.hs.entrydsm.application.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class ScoreNotFoundException extends MunchkinException {

    public ScoreNotFoundException() {
        super(ErrorCode.SCORE_NOT_FOUND);
    }
}
