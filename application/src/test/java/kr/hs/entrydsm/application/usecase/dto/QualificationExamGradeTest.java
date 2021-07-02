package kr.hs.entrydsm.application.usecase.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class QualificationExamGradeTest {

    private static final QualificationExamGrade GRADE_1 =
            new QualificationExamGrade(BigDecimal.valueOf(50.33));
    private static final QualificationExamGrade GRADE_2 =
            new QualificationExamGrade(BigDecimal.valueOf(11.51));

    @Test
    void getAverageScore() {
        assertEquals(GRADE_1.getAverageScore(), BigDecimal.valueOf(50.33));
        assertEquals(GRADE_2.getAverageScore(), BigDecimal.valueOf(11.51));
    }
}