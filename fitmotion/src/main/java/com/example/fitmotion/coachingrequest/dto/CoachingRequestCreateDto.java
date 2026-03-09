package com.example.fitmotion.coachingrequest.dto;

import jakarta.validation.constraints.NotNull;

public record CoachingRequestCreateDto(
        @NotNull Long memberId,
        @NotNull Long trainerId
) {
}