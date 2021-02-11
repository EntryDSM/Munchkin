package kr.hs.entrydsm.score.integrate.application;

import kr.hs.entrydsm.score.integrate.user.Scorer;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@AllArgsConstructor
public class GraduationCase extends ApplicationCase {

    @Builder(builderMethodName = "builder")
    public static GraduationCase initializer(Scorer scorer,
                                             Integer volunteerTime,
                                             Integer dayAbsenceCount,
                                             Integer lectureAbsenceCount,
                                             Integer latenessCount,
                                             Integer earlyLeaveCount,
                                             String koreanGrade,
                                             String socialGrade,
                                             String historyGrade,
                                             String mathGrade,
                                             String scienceGrade,
                                             String englishGrade,
                                             String techAndHomeGrade) {

        GraduationCase graduationCase = new GraduationCase(scorer,
                                                           volunteerTime,
                                                           dayAbsenceCount,
                                                           lectureAbsenceCount,
                                                           latenessCount,
                                                           earlyLeaveCount,
                                                           koreanGrade,
                                                           socialGrade,
                                                           historyGrade,
                                                           mathGrade,
                                                           scienceGrade,
                                                           englishGrade,
                                                           techAndHomeGrade);
        return nullCheck(graduationCase);
    }


    private final Scorer scorer;

    private final Integer volunteerTime;

    private final Integer dayAbsenceCount;
    private final Integer lectureAbsenceCount;
    private final Integer latenessCount;
    private final Integer earlyLeaveCount;

    private final String koreanGrade;
    private final String socialGrade;
    private final String historyGrade;
    private final String mathGrade;
    private final String scienceGrade;
    private final String englishGrade;
    private final String techAndHomeGrade;

    @Override
    public BigDecimal calculateVolunteerScore() { return volunteerScoreFormula(); }

    @Override
    public Integer calculateAttendanceScore() { return attendanceScoreFormula(); }

    @Override
    public BigDecimal calculateGradeScore() { return gradeScoreFormula(); }

    private BigDecimal volunteerScoreFormula() {
        if (volunteerTime >= MAX_VOLUNTEER_TIME) {
            return BigDecimal.valueOf(MAX_VOLUNTEER_SCORE);
        } else if (MIN_VOLUNTEER_TIME <= volunteerTime) {
            return BigDecimal.valueOf(volunteerTime)
                             .subtract(BigDecimal.valueOf(9))
                             .divide(BigDecimal.valueOf(3), 3, RoundingMode.HALF_UP)
                             .add(BigDecimal.valueOf(3));
        } else {
            return BigDecimal.valueOf(MIN_VOLUNTEER_SCORE);
        }
    }

    private Integer attendanceScoreFormula() {
        return Math.max((MAX_ATTENDANCE_SCORE -
                         dayAbsenceCount -
                         (lectureAbsenceCount + latenessCount + earlyLeaveCount) / 3),
                        0);
    }

    private BigDecimal gradeScoreFormula() {
        BigDecimal gradeScore = BigDecimal.ZERO;
        BigDecimal[] scoresPerYear = zeroCheckedScorePerYear();
        BigDecimal multiple = BigDecimal.valueOf(4.5);

        for (int i = 0 ; i < scoresPerYear.length ; i++) {
            if (i == scoresPerYear().length - 1) { multiple = BigDecimal.valueOf(6); }
            gradeScore = scoresPerYear[i].multiply(multiple).add(gradeScore);
        }

        if (scorer.isMeister()) {
            return gradeScore.multiply(BigDecimal.valueOf(0.6)).setScale(3, RoundingMode.HALF_UP);
        } else {
            return gradeScore.setScale(3, RoundingMode.HALF_UP);
        }
    }

    private BigDecimal[] zeroCheckedScorePerYear() {
        BigDecimal[] scoresPerYear = scoresPerYear();
        BigDecimal summedScore = BigDecimal.ZERO;

        for (BigDecimal scorePerYear: scoresPerYear) { summedScore = summedScore.add(scorePerYear); }

        if (scoresPerYear[0].equals(BigDecimal.ZERO) && scoresPerYear[1].equals(BigDecimal.ZERO)) {
            scoresPerYear[0] = scoresPerYear[2];
            scoresPerYear[1] = scoresPerYear[2];
        } else if (scoresPerYear[0].equals(BigDecimal.ZERO)) {
            scoresPerYear[0] = summedScore.divide(BigDecimal.valueOf(2), 4, RoundingMode.HALF_UP);
        } else if (scoresPerYear[1].equals(BigDecimal.ZERO)) {
            scoresPerYear[1] = summedScore.divide(BigDecimal.valueOf(2), 4, RoundingMode.HALF_UP);
        }

        return scoresPerYear;
    }

    private BigDecimal[] scoresPerYear() {
        BigDecimal[] scoresPerSemester = scoresPerSemester();
        BigDecimal[] scoresPerYear = new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO};

        for (int semester = 0 ; semester < scoresPerSemester.length ; semester += 2) {
            if (scoresPerSemester[semester].equals(BigDecimal.ZERO)) {
                scoresPerSemester[semester] = scoresPerSemester[semester + 1];
            } else if (scoresPerSemester[semester + 1].equals(BigDecimal.ZERO)) {
                scoresPerSemester[semester + 1] = scoresPerSemester[semester];
            }

            scoresPerYear[semester/2] = scoresPerSemester[semester].add(scoresPerSemester[semester+1]);
        }

        return scoresPerYear;
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

        if (!scorer.isGraduated()) gradesPerSemester[5] = gradesPerSemester[4];

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
                                 RoundingMode.HALF_UP);
    }

}
