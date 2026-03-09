package com.example.fitmotion.coachingrequest.repository;

import com.example.fitmotion.coachingrequest.entity.CoachingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachingRequestRepository extends JpaRepository<CoachingRequest, Long> {

    List<CoachingRequest> findByMemberIdOrderByCreatedAtDesc(Long memberId);

    List<CoachingRequest> findByTrainerIdOrderByCreatedAtDesc(Long trainerId);
}
