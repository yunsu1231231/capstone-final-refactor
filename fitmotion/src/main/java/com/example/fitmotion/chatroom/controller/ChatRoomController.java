package com.example.fitmotion.chatroom.controller;

import com.example.fitmotion.chatroom.dto.ChatRoomResponseDto;
import com.example.fitmotion.chatroom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat-rooms")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/{id}")
    public ChatRoomResponseDto get(@PathVariable Long id) {
        return chatRoomService.findById(id);
    }
}
