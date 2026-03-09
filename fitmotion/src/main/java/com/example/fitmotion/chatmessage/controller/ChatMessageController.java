package com.example.fitmotion.chatmessage.controller;

import com.example.fitmotion.chatmessage.dto.ChatMessageResponseDto;
import com.example.fitmotion.chatmessage.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat-rooms/{chatRoomId}/messages")
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @GetMapping
    public List<ChatMessageResponseDto> list(@PathVariable Long chatRoomId) {
        return chatMessageService.findByChatRoomId(chatRoomId);
    }
}
