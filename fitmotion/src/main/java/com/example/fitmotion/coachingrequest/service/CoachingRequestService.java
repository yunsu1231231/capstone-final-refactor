package com.example.fitmotion.coachingrequest.service;

import com.example.fitmotion.coachingrequest.dto.CoachingRequestCreateDto;
import com.example.fitmotion.coachingrequest.dto.CoachingRequestResponseDto;
import com.example.fitmotion.coachingrequest.dto.CoachingRequestStatusUpdateDto;

import java.util.List;

public interface CoachingRequestService {

    CoachingRequestResponseDto create(CoachingRequestCreateDto dto);

    List<CoachingRequestResponseDto> findByMemberId(Long memberId);

    List<CoachingRequestResponseDto> findByTrainerId(Long trainerId);

    CoachingRequestResponseDto updateStatus(Long requestId, CoachingRequestStatusUpdateDto dto);
}
