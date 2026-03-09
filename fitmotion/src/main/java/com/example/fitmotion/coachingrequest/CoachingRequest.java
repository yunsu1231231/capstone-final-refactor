package com.example.fitmotion.coachingrequest;

import com.example.fitmotion.chatroom.ChatRoom;
import com.example.fitmotion.enums.RequestStatus;
import com.example.fitmotion.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoachingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Member trainer;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDateTime createdAt;

    public void updateStatus(RequestStatus status) {
        this.status = status;
    }

    @OneToOne(mappedBy = "coachingRequest")
    private ChatRoom chatRoom;

    /** 연관관계 편의 메서드: 회원 설정 시 양방향 동기화 */
    public void setMember(Member member) {
        if (this.member == member) return;
        if (this.member != null) {
            this.member.getMemberRequests().remove(this);
        }
        this.member = member;
        if (member != null) {
            member.getMemberRequests().add(this);
        }
    }

    /** 연관관계 편의 메서드: 트레이너 설정 시 양방향 동기화 */
    public void setTrainer(Member trainer) {
        if (this.trainer == trainer) return;
        if (this.trainer != null) {
            this.trainer.getTrainerRequests().remove(this);
        }
        this.trainer = trainer;
        if (trainer != null) {
            trainer.getTrainerRequests().add(this);
        }
    }

    /** 연관관계 편의 메서드: 채팅방 설정 시 양방향 동기화 (요청 수락 시 호출) */
    public void setChatRoom(ChatRoom chatRoom) {
        if (this.chatRoom == chatRoom) return;
        if (this.chatRoom != null) {
            this.chatRoom.setCoachingRequest(null);
        }
        this.chatRoom = chatRoom;
        if (chatRoom != null && chatRoom.getCoachingRequest() != this) {
            chatRoom.setCoachingRequest(this);
        }
    }
}
