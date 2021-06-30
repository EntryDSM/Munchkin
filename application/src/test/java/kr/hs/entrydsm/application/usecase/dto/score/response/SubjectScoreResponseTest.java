package kr.hs.entrydsm.application.usecase.dto.score.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubjectScoreResponseTest {

    private static final SubjectScoreResponse RESPONSE_1 = SubjectScoreResponse.builder()
            .koreanScore("AAAXAA")
            .socialScore("AAAXAA")
            .historyScore("AAAXAA")
            .mathScore("AAAXAA")
            .scienceScore("AAAXAA")
            .englishScore("AAAXAA")
            .techAndHomeScore("AAAXAA")
            .build();

    private static final SubjectScoreResponse RESPONSE_2 = SubjectScoreResponse.builder()
            .koreanScore("BBBXBB")
            .socialScore("BBBXBB")
            .historyScore("BBBXBB")
            .mathScore("BBBXBB")
            .scienceScore("BBBXBB")
            .englishScore("BBBXBB")
            .techAndHomeScore("BBBXBB")
            .build();

    private static final SubjectScoreResponse RESPONSE_3 = SubjectScoreResponse.builder()
            .koreanScore("CCCXCC")
            .socialScore("CCCXCC")
            .historyScore("CCCXCC")
            .mathScore("CCCXCC")
            .scienceScore("CCCXCC")
            .englishScore("CCCXCC")
            .techAndHomeScore("CCCXCC")
            .build();

    private static final SubjectScoreResponse RESPONSE = new SubjectScoreResponse();

    @Test
    void getKoreanScore() {
        assertEquals(RESPONSE_1.getKoreanScore(), "AAAXAA");
        assertEquals(RESPONSE_2.getKoreanScore(), "BBBXBB");
        assertEquals(RESPONSE_3.getKoreanScore(), "CCCXCC");
        assertNull(RESPONSE.getKoreanScore());
    }

    @Test
    void getSocialScore() {
        assertEquals(RESPONSE_1.getSocialScore(), "AAAXAA");
        assertEquals(RESPONSE_2.getSocialScore(), "BBBXBB");
        assertEquals(RESPONSE_3.getSocialScore(), "CCCXCC");
        assertNull(RESPONSE.getSocialScore());
    }

    @Test
    void getHistoryScore() {
        assertEquals(RESPONSE_1.getHistoryScore(), "AAAXAA");
        assertEquals(RESPONSE_2.getHistoryScore(), "BBBXBB");
        assertEquals(RESPONSE_3.getHistoryScore(), "CCCXCC");
        assertNull(RESPONSE.getHistoryScore());
    }

    @Test
    void getMathScore() {
        assertEquals(RESPONSE_1.getMathScore(), "AAAXAA");
        assertEquals(RESPONSE_2.getMathScore(), "BBBXBB");
        assertEquals(RESPONSE_3.getMathScore(), "CCCXCC");
        assertNull(RESPONSE.getMathScore());
    }

    @Test
    void getScienceScore() {
        assertEquals(RESPONSE_1.getScienceScore(), "AAAXAA");
        assertEquals(RESPONSE_2.getScienceScore(), "BBBXBB");
        assertEquals(RESPONSE_3.getScienceScore(), "CCCXCC");
        assertNull(RESPONSE.getScienceScore());
    }

    @Test
    void getEnglishScore() {
        assertEquals(RESPONSE_1.getEnglishScore(), "AAAXAA");
        assertEquals(RESPONSE_2.getEnglishScore(), "BBBXBB");
        assertEquals(RESPONSE_3.getEnglishScore(), "CCCXCC");
        assertNull(RESPONSE.getEnglishScore());
    }

    @Test
    void getTechAndHomeScore() {
        assertEquals(RESPONSE_1.getTechAndHomeScore(), "AAAXAA");
        assertEquals(RESPONSE_2.getTechAndHomeScore(), "BBBXBB");
        assertEquals(RESPONSE_3.getTechAndHomeScore(), "CCCXCC");
        assertNull(RESPONSE.getTechAndHomeScore());
    }
}