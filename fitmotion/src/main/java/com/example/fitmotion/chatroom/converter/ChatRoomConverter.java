package com.example.fitmotion.chatroom.converter;

import com.example.fitmotion.chatroom.dto.ChatRoomResponseDto;
import com.example.fitmotion.chatroom.entity.ChatRoom;
import org.springframework.stereotype.Component;

@Component
public class ChatRoomConverter {

    public ChatRoomResponseDto toResponseDto(ChatRoom entity) {
        if (entity == null) return null;
        return new ChatRoomResponseDto(
                entity.getId(),
                entity.getCoachingRequest() != null ? entity.getCoachingRequest().getId() : null,
                entity.getCreatedAt()
        );
    }
}
