package com.example.fitmotion.chatroom.dto;

import java.time.LocalDateTime;

public record ChatRoomResponseDto(
        Long id,
        Long coachingRequestId,
        LocalDateTime createdAt
) {
}
