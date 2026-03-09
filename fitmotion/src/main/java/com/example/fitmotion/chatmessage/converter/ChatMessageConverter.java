package com.example.fitmotion.chatmessage.converter;

import com.example.fitmotion.chatmessage.dto.ChatMessageResponseDto;
import com.example.fitmotion.chatmessage.entity.ChatMessage;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageConverter {

    public ChatMessageResponseDto toResponseDto(ChatMessage entity) {
        if (entity == null) return null;
        return new ChatMessageResponseDto(
                entity.getId(),
                entity.getSender() != null ? entity.getSender().getId() : null,
                entity.getSender() != null ? entity.getSender().getName() : null,
                entity.getContent(),
                entity.getCreatedAt()
        );
    }
}
