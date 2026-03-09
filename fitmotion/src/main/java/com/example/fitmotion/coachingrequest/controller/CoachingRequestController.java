package com.example.fitmotion.coachingrequest.controller;

import com.example.fitmotion.coachingrequest.dto.CoachingRequestCreateDto;
import com.example.fitmotion.coachingrequest.dto.CoachingRequestResponseDto;
import com.example.fitmotion.coachingrequest.dto.CoachingRequestStatusUpdateDto;
import com.example.fitmotion.coachingrequest.service.CoachingRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coaching-requests")
@RequiredArgsConstructor
public class CoachingRequestController {

    private final CoachingRequestService coachingRequestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CoachingRequestResponseDto create(@Valid @RequestBody CoachingRequestCreateDto dto) {
        return coachingRequestService.create(dto);
    }

    @GetMapping(params = "memberId")
    public List<CoachingRequestResponseDto> listByMember(@RequestParam Long memberId) {
        return coachingRequestService.findByMemberId(memberId);
    }

    @GetMapping(params = "trainerId")
    public List<CoachingRequestResponseDto> listByTrainer(@RequestParam Long trainerId) {
        return coachingRequestService.findByTrainerId(trainerId);
    }

    @PatchMapping("/{requestId}/status")
    public CoachingRequestResponseDto updateStatus(
            @PathVariable Long requestId,
            @Valid @RequestBody CoachingRequestStatusUpdateDto dto) {
        return coachingRequestService.updateStatus(requestId, dto);
    }
}
