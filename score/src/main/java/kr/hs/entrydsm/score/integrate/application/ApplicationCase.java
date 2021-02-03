package kr.hs.entrydsm.score.integrate.application;

import kr.hs.entrydsm.score.integrate.ExternalEntity;

import java.math.BigDecimal;

public abstract class ApplicationCase extends ExternalEntity {
    protected static final int MAX_VOLUNTEER_TIME = 45;
    protected static final int MIN_VOLUNTEER_TIME = 10;

    protected static final int MAX_VOLUNTEER_SCORE = 15;
    protected static final int MIN_VOLUNTEER_SCORE = 3;

    protected static final int MAX_ATTENDANCE_SCORE = 15;

    abstract public BigDecimal calculateVolunteerScore();
    abstract public Integer calculateAttendanceScore();
    abstract public BigDecimal calculateGradeScore();
}
