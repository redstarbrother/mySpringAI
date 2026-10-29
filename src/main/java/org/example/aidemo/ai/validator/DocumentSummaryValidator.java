package org.example.aidemo.ai.validator;

import org.example.aidemo.ai.entity.DocumentSummary;
import org.springframework.stereotype.Component;

@Component
public class DocumentSummaryValidator {

    public void validate(DocumentSummary result) {
        if (result == null) {
            throw new IllegalArgumentException("摘要结果不能为空");
        }

        if (result.title() == null || result.title().isBlank()) {
            throw new IllegalArgumentException("摘要标题不能为空");
        }

        if (result.summary() == null || result.summary().isBlank()) {
            throw new IllegalArgumentException("摘要内容不能为空");
        }

        if (result.keyPoints() == null) {
            throw new IllegalArgumentException("关键要点不能为空");
        }

        if (result.riskWarnings() == null) {
            throw new IllegalArgumentException("风险提示不能为空");
        }
    }
}