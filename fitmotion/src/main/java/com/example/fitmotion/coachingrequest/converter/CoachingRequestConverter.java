package com.example.fitmotion.coachingrequest.converter;

import com.example.fitmotion.coachingrequest.dto.CoachingRequestResponseDto;
import com.example.fitmotion.coachingrequest.entity.CoachingRequest;
import org.springframework.stereotype.Component;

@Component
public class CoachingRequestConverter {

    public CoachingRequestResponseDto toResponseDto(CoachingRequest entity) {
        if (entity == null) return null;
        return new CoachingRequestResponseDto(
                entity.getId(),
                entity.getMember() != null ? entity.getMember().getId() : null,
                entity.getMember() != null ? entity.getMember().getName() : null,
                entity.getTrainer() != null ? entity.getTrainer().getId() : null,
                entity.getTrainer() != null ? entity.getTrainer().getName() : null,
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getChatRoom() != null ? entity.getChatRoom().getId() : null
        );
    }
}
