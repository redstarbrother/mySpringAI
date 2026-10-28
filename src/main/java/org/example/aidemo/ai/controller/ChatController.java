package org.example.aidemo.ai.controller;

import jakarta.annotation.Resource;
import org.example.aidemo.ai.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class ChatController {

    @Resource
    private ChatService chatService;

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest chatRequest) {
        String answer = chatService.summarize(chatRequest.document(), chatRequest.document());
        return new ChatResponse(answer);
    }

    public record ChatRequest(String document, String style) {
    }

    public record ChatResponse(String answer) {
    }
}
