package com.example.fitmotion.chatmessage.dto;

import java.time.LocalDateTime;

public record ChatMessageResponseDto(
        Long id,
        Long senderId,
        String senderName,
        String content,
        LocalDateTime createdAt
) {
}
