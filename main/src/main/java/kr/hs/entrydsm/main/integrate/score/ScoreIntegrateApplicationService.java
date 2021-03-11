package kr.hs.entrydsm.main.integrate.score;

import kr.hs.entrydsm.score.integrate.application.ApplicationCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class ScoreIntegrateApplicationService extends ApplicationCase {
    @Override
    public BigDecimal calculateVolunteerScore() {
        return null;
    }

    @Override
    public Integer calculateAttendanceScore() {
        return null;
    }

    @Override
    public BigDecimal calculateGradeScore() {
        return null;
    }
}
