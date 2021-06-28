package kr.hs.entrydsm.admin.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class ScheduleNotFoundException extends MunchkinException {
    public ScheduleNotFoundException() {
        super(ErrorCode.SCHEDULE_NOT_FOUND);
    }
}
