package com.example.fitmotion.coachingrequest.entity;

import com.example.fitmotion.chatroom.entity.ChatRoom;
import com.example.fitmotion.enums.RequestStatus;
import com.example.fitmotion.member.entity.Member;
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

    @OneToOne(mappedBy = "coachingRequest")
    private ChatRoom chatRoom;

    public void updateStatus(RequestStatus status) {
        this.status = status;
    }

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
