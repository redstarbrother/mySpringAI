package org.example.aidemo.ai.service;

import org.example.aidemo.ai.entity.DocumentSummary;
import org.example.aidemo.ai.validator.DocumentSummaryValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatServiceTest {

    private final DocumentSummaryValidator validator =
            new DocumentSummaryValidator();

    @Test
    void shouldAcceptValidSummary() {
        DocumentSummary summary = new DocumentSummary(
                "信息安全制度",
                "管理制度",
                "规定了信息处理要求。",
                List.of("敏感信息需要受到保护"),
                List.of()
        );

        assertDoesNotThrow(() -> validator.validate(summary));
    }

    @Test
    void shouldRejectBlankTitle() {
        DocumentSummary summary = new DocumentSummary(
                " ",
                "管理制度",
                "规定了信息处理要求。",
                List.of("敏感信息需要受到保护"),
                List.of()
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(summary)
        );
    }

}