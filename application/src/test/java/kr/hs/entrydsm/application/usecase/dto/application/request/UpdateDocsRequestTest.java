package kr.hs.entrydsm.application.usecase.dto.application.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateDocsRequestTest {

    private static final UpdateDocsRequest docs1 = new UpdateDocsRequest("test1");

    private static final UpdateDocsRequest docs2 = new UpdateDocsRequest("test2");

    private static final UpdateDocsRequest docs3 = new UpdateDocsRequest("test3");

    @Test
    void getContent() {
        assertEquals(docs1.getContent(), "test1");
        assertEquals(docs2.getContent(), "test2");
        assertEquals(docs3.getContent(), "test3");
    }
}