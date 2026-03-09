package com.example.fitmotion.coachingrequest.service;

import com.example.fitmotion.chatroom.entity.ChatRoom;
import com.example.fitmotion.chatroom.repository.ChatRoomRepository;
import com.example.fitmotion.coachingrequest.converter.CoachingRequestConverter;
import com.example.fitmotion.coachingrequest.dto.CoachingRequestCreateDto;
import com.example.fitmotion.coachingrequest.dto.CoachingRequestResponseDto;
import com.example.fitmotion.coachingrequest.dto.CoachingRequestStatusUpdateDto;
import com.example.fitmotion.coachingrequest.entity.CoachingRequest;
import com.example.fitmotion.coachingrequest.repository.CoachingRequestRepository;
import com.example.fitmotion.enums.RequestStatus;
import com.example.fitmotion.member.entity.Member;
import com.example.fitmotion.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CoachingRequestServiceImpl implements CoachingRequestService {

    private final CoachingRequestRepository coachingRequestRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final CoachingRequestConverter converter;

    @Override
    @Transactional
    public CoachingRequestResponseDto create(CoachingRequestCreateDto dto) {
        Member member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. id=" + dto.memberId()));
        Member trainer = memberRepository.findById(dto.trainerId())
                .orElseThrow(() -> new IllegalArgumentException("트레이너를 찾을 수 없습니다. id=" + dto.trainerId()));

        CoachingRequest request = CoachingRequest.builder()
                .status(RequestStatus.REQUESTED)
                .createdAt(LocalDateTime.now())
                .build();
        request.setMember(member);
        request.setTrainer(trainer);

        CoachingRequest saved = coachingRequestRepository.save(request);
        return converter.toResponseDto(saved);
    }

    @Override
    public List<CoachingRequestResponseDto> findByMemberId(Long memberId) {
        return coachingRequestRepository.findByMemberIdOrderByCreatedAtDesc(memberId).stream()
                .map(converter::toResponseDto)
                .toList();
    }

    @Override
    public List<CoachingRequestResponseDto> findByTrainerId(Long trainerId) {
        return coachingRequestRepository.findByTrainerIdOrderByCreatedAtDesc(trainerId).stream()
                .map(converter::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public CoachingRequestResponseDto updateStatus(Long requestId, CoachingRequestStatusUpdateDto dto) {
        CoachingRequest request = coachingRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("코칭 요청을 찾을 수 없습니다. id=" + requestId));

        if (request.getStatus() != RequestStatus.REQUESTED) {
            throw new IllegalStateException("이미 처리된 요청입니다. status=" + request.getStatus());
        }

        if (dto.status() == RequestStatus.ACCEPTED) {
            ChatRoom room = ChatRoom.builder()
                    .createdAt(LocalDateTime.now())
                    .build();
            room.setCoachingRequest(request);
            chatRoomRepository.save(room);
        }
        request.updateStatus(dto.status());
        CoachingRequest saved = coachingRequestRepository.save(request);
        return converter.toResponseDto(saved);
    }
}
