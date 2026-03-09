package com.example.fitmotion.chatroom;

import com.example.fitmotion.chatmessage.ChatMessage;
import com.example.fitmotion.coachingrequest.CoachingRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coaching_request_id")
    private CoachingRequest coachingRequest;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ChatMessage> messages = new ArrayList<>();

    /** 연관관계 편의 메서드: 코칭 요청 설정 시 양방향 동기화 (채팅방 생성 시 호출) */
    public void setCoachingRequest(CoachingRequest coachingRequest) {
        if (this.coachingRequest == coachingRequest) return;
        if (this.coachingRequest != null) {
            this.coachingRequest.setChatRoom(null);
        }
        this.coachingRequest = coachingRequest;
        if (coachingRequest != null && coachingRequest.getChatRoom() != this) {
            coachingRequest.setChatRoom(this);
        }
    }

    /** 연관관계 편의 메서드: 메시지 추가 시 양방향 동기화 */
    public void addMessage(ChatMessage message) {
        this.messages.add(message);
        if (message.getChatRoom() != this) {
            message.setChatRoom(this);
        }
    }
}
