package kr.hs.entrydsm.common.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GraduationReportCard extends ReportCard {

    private final boolean isGraduated;
    private final String schoolName;
    private final String schoolTel;
    private final int volunteerTime;
    private final int latenessCount;
    private final int earlyLeaveCount;
    private final int lectureAbsenceCount;
    private final int dayAbsenceCount;

    @Builder
    public GraduationReportCard(long receiptCode, Scores scores, boolean isGraduated, String schoolName, String schoolTel,
                                int volunteerTime, int latenessCount, int earlyLeaveCount, int lectureAbsenceCount, int dayAbsenceCount) {
        super(receiptCode, scores);
        this.isGraduated = isGraduated;
        this.schoolName = schoolName;
        this.schoolTel = schoolTel;
        this.volunteerTime = volunteerTime;
        this.latenessCount = latenessCount;
        this.earlyLeaveCount = earlyLeaveCount;
        this.lectureAbsenceCount = lectureAbsenceCount;
        this.dayAbsenceCount = dayAbsenceCount;
    }

    @Override
    public boolean isGraduation() {
        return true;
    }
}
