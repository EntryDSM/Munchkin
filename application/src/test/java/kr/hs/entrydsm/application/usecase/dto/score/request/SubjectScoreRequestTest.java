package kr.hs.entrydsm.application.usecase.dto.score.request;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubjectScoreRequestTest {

    private static final GraduationApplication APPLICATION = new GraduationApplication();

    private static final SubjectScoreRequest REQUEST_1 = SubjectScoreRequest.builder()
            .koreanScore("AAAXAA")
            .socialScore("AAAXAA")
            .historyScore("AAAXAA")
            .mathScore("AAAXAA")
            .scienceScore("AAAXAA")
            .englishScore("AAAXAA")
            .techAndHomeScore("AAAXAA")
            .build();

    private static final SubjectScoreRequest REQUEST_2 = SubjectScoreRequest.builder()
            .koreanScore("BBBXBB")
            .socialScore("BBBXBB")
            .historyScore("BBBXBB")
            .mathScore("BBBXBB")
            .scienceScore("BBBXBB")
            .englishScore("BBBXBB")
            .techAndHomeScore("BBBXBB")
            .build();

    private static final SubjectScoreRequest REQUEST_3 = SubjectScoreRequest.builder()
            .koreanScore("CCCXCC")
            .socialScore("CCCXCC")
            .historyScore("CCCXCC")
            .mathScore("CCCXCC")
            .scienceScore("CCCXCC")
            .englishScore("CCCXCC")
            .techAndHomeScore("CCCXCC")
            .build();

    private static final SubjectScoreRequest REQUEST = new SubjectScoreRequest();

    @Test
    void from() {
        assertNull(SubjectScoreRequest.from(APPLICATION).getEnglishScore());
    }

    @Test
    void getKoreanScore() {
        assertEquals(REQUEST_1.getKoreanScore(), "AAAXAA");
        assertEquals(REQUEST_2.getKoreanScore(), "BBBXBB");
        assertEquals(REQUEST_3.getKoreanScore(), "CCCXCC");
        assertNull(REQUEST.getKoreanScore());
    }

    @Test
    void getSocialScore() {
        assertEquals(REQUEST_1.getSocialScore(), "AAAXAA");
        assertEquals(REQUEST_2.getSocialScore(), "BBBXBB");
        assertEquals(REQUEST_3.getSocialScore(), "CCCXCC");
        assertNull(REQUEST.getSocialScore());
    }

    @Test
    void getHistoryScore() {
        assertEquals(REQUEST_1.getHistoryScore(), "AAAXAA");
        assertEquals(REQUEST_2.getHistoryScore(), "BBBXBB");
        assertEquals(REQUEST_3.getHistoryScore(), "CCCXCC");
        assertNull(REQUEST.getHistoryScore());
    }

    @Test
    void getMathScore() {
        assertEquals(REQUEST_1.getMathScore(), "AAAXAA");
        assertEquals(REQUEST_2.getMathScore(), "BBBXBB");
        assertEquals(REQUEST_3.getMathScore(), "CCCXCC");
        assertNull(REQUEST.getMathScore());
    }

    @Test
    void getScienceScore() {
        assertEquals(REQUEST_1.getScienceScore(), "AAAXAA");
        assertEquals(REQUEST_2.getScienceScore(), "BBBXBB");
        assertEquals(REQUEST_3.getScienceScore(), "CCCXCC");
        assertNull(REQUEST.getScienceScore());
    }

    @Test
    void getEnglishScore() {
        assertEquals(REQUEST_1.getEnglishScore(), "AAAXAA");
        assertEquals(REQUEST_2.getEnglishScore(), "BBBXBB");
        assertEquals(REQUEST_3.getEnglishScore(), "CCCXCC");
        assertNull(REQUEST.getEnglishScore());
    }

    @Test
    void getTechAndHomeScore() {
        assertEquals(REQUEST_1.getTechAndHomeScore(), "AAAXAA");
        assertEquals(REQUEST_2.getTechAndHomeScore(), "BBBXBB");
        assertEquals(REQUEST_3.getTechAndHomeScore(), "CCCXCC");
        assertNull(REQUEST.getTechAndHomeScore());
    }
}