package com.example.fitmotion.coachingrequest.dto;

import com.example.fitmotion.enums.RequestStatus;

import jakarta.validation.constraints.NotNull;

public record CoachingRequestStatusUpdateDto(
        @NotNull RequestStatus status
) {
}
