package kr.hs.entrydsm.score.domain.entity.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GraduationCase {
    private long receiptCode;

    private Integer volunteerTime;
    private Integer dayAbsenceCount;
    private Integer lectureAbsenceCount;
    private Integer latenessCount;
    private Integer earlyLeaveCount;

    private String koreanScore;
    private String socialScore;
    private String historyScore;
    private String mathScore;
    private String scienceScore;
    private String englishScore;
    private String techAndHomeScore;
}
