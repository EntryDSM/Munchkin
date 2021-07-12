package kr.hs.entrydsm.score.entity;

import kr.hs.entrydsm.score.integrate.user.Scorer;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

@Getter
@NoArgsConstructor
@Entity(name = "tbl_graduation_case")
public class GraduationCase extends ApplicationCase {
    private Integer volunteerTime;

    private Integer dayAbsenceCount;
    private Integer lectureAbsenceCount;
    private Integer latenessCount;
    private Integer earlyLeaveCount;

    private String koreanGrade;
    private String socialGrade;
    private String historyGrade;
    private String mathGrade;
    private String scienceGrade;
    private String englishGrade;
    private String techAndHomeGrade;

    @Builder
    public GraduationCase(Scorer scorer, Integer volunteerTime, Integer dayAbsenceCount,
                          Integer lectureAbsenceCount, Integer latenessCount,
                          Integer earlyLeaveCount, String koreanGrade,
                          String socialGrade, String historyGrade,
                          String mathGrade, String scienceGrade,
                          String englishGrade, String techAndHomeGrade) {
        super(scorer);
        this.volunteerTime = volunteerTime;
        this.dayAbsenceCount = dayAbsenceCount;
        this.lectureAbsenceCount = lectureAbsenceCount;
        this.latenessCount = latenessCount;
        this.earlyLeaveCount = earlyLeaveCount;
        this.koreanGrade = koreanGrade;
        this.socialGrade = socialGrade;
        this.historyGrade = historyGrade;
        this.mathGrade = mathGrade;
        this.scienceGrade = scienceGrade;
        this.englishGrade = englishGrade;
        this.techAndHomeGrade = techAndHomeGrade;
    }

    @Transient
    private final BigDecimal GRADE_RATE = BigDecimal.valueOf(4);

    @Override
    public BigDecimal calculateVolunteerScore() { return volunteerScoreFormula(); }

    @Override
    public Integer calculateAttendanceScore() { return attendanceScoreFormula(); }

    @Override
    public BigDecimal[] calculateGradeScores() { return gradeScoreFormula(); }

    @Override
    public BigDecimal calculateTotalGradeScore() { return totalGradeScoreFormula(); }

    private BigDecimal volunteerScoreFormula() {
        if (volunteerTime >= MAX_VOLUNTEER_TIME) {
            return BigDecimal.valueOf(MAX_VOLUNTEER_SCORE);
        } else if (MIN_VOLUNTEER_TIME <= volunteerTime) {
            return BigDecimal.valueOf(volunteerTime)
                             .subtract(BigDecimal.valueOf(6))
                             .divide(BigDecimal.valueOf(2), 3, RoundingMode.HALF_UP)
                             .add(BigDecimal.valueOf(3));
        } else {
            return BigDecimal.valueOf(MIN_VOLUNTEER_SCORE);
        }
    }

    private Integer attendanceScoreFormula() {
        return Math.max((MAX_ATTENDANCE_SCORE
                        - dayAbsenceCount
                        - (lectureAbsenceCount + latenessCount + earlyLeaveCount) / 3),
                        0);
    }

    private BigDecimal totalGradeScoreFormula() {
        BigDecimal scoresSum = BigDecimal.ZERO;

        for (BigDecimal score: gradeScoreFormula()) {
            scoresSum = scoresSum.add(score);
        }

        if (scorer.isCommon()) {
            scoresSum = scoresSum.multiply(COMMON_GRADE_RATE);
        }

        return scoresSum.setScale(3, RoundingMode.HALF_UP);
    }

    private BigDecimal[] gradeScoreFormula() {
        BigDecimal[] gradeScores = new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO};
        BigDecimal[] scoresToCalculate = zeroCheckScoresToCalculate();

        for (int index = 0; index < scoresToCalculate().length; index++) {
            gradeScores[index] = scoresToCalculate[index].multiply(GRADE_RATE)
                    .setScale(3, RoundingMode.HALF_UP);
        }

        return gradeScores;
    }

    private BigDecimal[] zeroCheckScoresToCalculate() {
        BigDecimal[] scoresToCalculate = scoresToCalculate();
        BigDecimal summedScore = BigDecimal.ZERO;

        for (BigDecimal scorePerYear: scoresToCalculate) {
            summedScore = summedScore.add(scorePerYear);
        }

        if (scoresToCalculate[0].equals(BigDecimal.ZERO) && scoresToCalculate[1].equals(BigDecimal.ZERO)) {
            BigDecimal scoreToReplace = summedScore.divide(BigDecimal.valueOf(2), 4, RoundingMode.DOWN);
            scoresToCalculate[0] = scoreToReplace;
            scoresToCalculate[1] = scoreToReplace;
        } else if (scoresToCalculate[0].equals(BigDecimal.ZERO)) {
            scoresToCalculate[0] = summedScore.divide(BigDecimal.valueOf(3), 4, RoundingMode.DOWN);
        } else if (scoresToCalculate[1].equals(BigDecimal.ZERO)) {
            scoresToCalculate[1] = summedScore.divide(BigDecimal.valueOf(3), 4, RoundingMode.DOWN);
        }

        return scoresToCalculate;
    }

    private BigDecimal[] scoresToCalculate() {
        BigDecimal[] scoresPerSemester = scoresPerSemester();
        BigDecimal[] scoresToCalculate = new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO};

        int toCalculateLength = scoresToCalculate.length;
        int semesterLength = scoresPerSemester.length;

        scoresToCalculate[toCalculateLength - 1] = scoresPerSemester[semesterLength - 1];
        if (scoresToCalculate[toCalculateLength - 1].equals(BigDecimal.ZERO)) {
            scoresToCalculate[toCalculateLength - 1] = scoresPerSemester[semesterLength - 2];
        }

        for (int semesterIndex = semesterLength - 2, scoreIndex = toCalculateLength - 2;
             semesterIndex >= 0 && scoreIndex >= 0;
             semesterIndex--) {
            if (!scoresPerSemester[semesterIndex].equals(BigDecimal.ZERO)) {
                scoresToCalculate[scoreIndex--] = scoresPerSemester[semesterIndex];
            }
        }

        return scoresToCalculate;
    }

    private BigDecimal[] scoresPerSemester() {
        String[] gradesPerSemester = gradesPerSemester();
        BigDecimal[] scoresPerSemester = new BigDecimal[]{BigDecimal.ZERO,
                                                          BigDecimal.ZERO,
                                                          BigDecimal.ZERO,
                                                          BigDecimal.ZERO,
                                                          BigDecimal.ZERO,
                                                          BigDecimal.ZERO};

        for (int semester = 0 ; semester < gradesPerSemester.length ; semester++) {
            scoresPerSemester[semester] = gradesToScore(gradesPerSemester[semester]);
        }

        return scoresPerSemester;
    }

    private String[] gradesPerSemester() {
        String[] gradesPerSubject = new String[]{koreanGrade,
                                                 socialGrade,
                                                 historyGrade,
                                                 mathGrade,
                                                 scienceGrade,
                                                 englishGrade,
                                                 techAndHomeGrade};
        String[] gradesPerSemester = new String[]{"", "", "", "", "", ""};

        for (String grades: gradesPerSubject) {
            for (int semester = 0 ; semester < grades.length() ; semester++) {
                gradesPerSemester[semester] += grades.charAt(semester);
            }
        }

        if (scorer.isProspectiveGraduate()) gradesPerSemester[5] = gradesPerSemester[4];

        return gradesPerSemester;
    }

    private BigDecimal gradesToScore(String grades) {
        int semesterScore = 0;
        int subjectCount = 0;

        for (char grade: grades.toCharArray()) {
            switch (grade) {
                case 'A':
                    semesterScore++;
                case 'B':
                    semesterScore++;
                case 'C':
                    semesterScore++;
                case 'D':
                    semesterScore++;
                case 'E':
                    semesterScore++;
                    subjectCount++;
            }
        }

        if (semesterScore == 0) { return BigDecimal.ZERO; }

        return BigDecimal.valueOf(semesterScore)
                         .divide(BigDecimal.valueOf(subjectCount),
                           4,
                                 RoundingMode.DOWN);
    }

}
