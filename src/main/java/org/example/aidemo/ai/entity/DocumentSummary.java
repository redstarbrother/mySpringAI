package org.example.aidemo.ai.entity;

import java.util.List;

public record DocumentSummary(
        String title,
        String documentType,
        String summary,
        List<String> keyPoints,
        List<String> riskWarnings
) {
}
