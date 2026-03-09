package com.example.fitmotion.member;

import com.example.fitmotion.coachingrequest.CoachingRequest;
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

    /** 회원이 보낸 코칭 요청 */
    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<CoachingRequest> memberRequests = new ArrayList<>();

    /** 트레이너가 받은 요청 */
    @OneToMany(mappedBy = "trainer")
    @Builder.Default
    private List<CoachingRequest> trainerRequests = new ArrayList<>();
}
