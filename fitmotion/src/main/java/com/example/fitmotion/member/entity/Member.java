package com.example.fitmotion.member.entity;

import com.example.fitmotion.coachingrequest.entity.CoachingRequest;
import com.example.fitmotion.enums.MemberType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<CoachingRequest> memberRequests = new ArrayList<>();

    @OneToMany(mappedBy = "trainer")
    @Builder.Default
    private List<CoachingRequest> trainerRequests = new ArrayList<>();
}
