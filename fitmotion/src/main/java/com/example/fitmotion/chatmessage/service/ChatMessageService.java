package com.example.fitmotion.chatmessage.service;

import com.example.fitmotion.chatmessage.dto.ChatMessageResponseDto;

import java.util.List;

public interface ChatMessageService {

    ChatMessageResponseDto sendMessage(Long chatRoomId, Long senderId, String content);

    List<ChatMessageResponseDto> findByChatRoomId(Long chatRoomId);
}
