package com.example.fitmotion.coachingrequest.dto;

import com.example.fitmotion.enums.RequestStatus;

import java.time.LocalDateTime;

public record CoachingRequestResponseDto(
        Long id,
        Long memberId,
        String memberName,
        Long trainerId,
        String trainerName,
        RequestStatus status,
        LocalDateTime createdAt,
        Long chatRoomId
) {
}
