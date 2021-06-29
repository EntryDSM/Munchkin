package kr.hs.entrydsm.application.usecase.dto.application.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateDocsRequestTest {

    private static final UpdateDocsRequest DOCS_1 = new UpdateDocsRequest("test1");

    private static final UpdateDocsRequest DOCS_2 = new UpdateDocsRequest("test2");

    private static final UpdateDocsRequest DOCS_3 = new UpdateDocsRequest("test3");

    private static final UpdateDocsRequest REQUEST = new UpdateDocsRequest();

    @Test
    void getContent() {
        assertEquals(DOCS_1.getContent(), "test1");
        assertEquals(DOCS_2.getContent(), "test2");
        assertEquals(DOCS_3.getContent(), "test3");
        assertNull(REQUEST.getContent());
    }
}