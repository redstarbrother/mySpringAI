package org.example.aidemo.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/document-summary-user.st")
    private Resource dsu;

    @Value("classpath:/prompts/knowledge-qa-system.st")
    private Resource kqs;

    public ChatService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String chat(String document, String style) {
        return chatClient.prompt()
                .system("""
                        你是 KnowFlow 企业知识助手。
                        回答时请遵循以下要求：
                        1. 使用简体中文。
                        2. 优先给出准确、清晰、结构化的回答。
                        3. 不确定的信息要明确说明，不要编造事实。
                        4. 用户的问题不明确时，可以先提出澄清问题。
                        """)
                .user(user -> user.text("""
                        请使用 {style} 风格总结以下文档。
                        文档内容：
                        {document}
                        """).param("style", style)
                        .param("document", document))
                .call()
                .content();
    }

    public String summarize(String document, String style) {
        return chatClient.prompt()
                .system(kqs)
                .user(user -> user.text(dsu)
                        .param("style", style)
                        .param("document", document))
                .call()
                .content();
    }

    public String customOptions(String msg) {
        return chatClient.prompt()
                .user("this is user message")
                .options(ChatOptions.builder()
                        .temperature(0.2)
                        .maxTokens(1000).build())
                .call()
                .content();
    }
}
