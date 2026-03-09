package com.example.fitmotion.chatmessage;

import com.example.fitmotion.chatroom.ChatRoom;
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
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member sender;

    private String content;

    private LocalDateTime createdAt;

    /** 연관관계 편의 메서드: 채팅방 설정 시 양방향 동기화 */
    public void setChatRoom(ChatRoom chatRoom) {
        if (this.chatRoom == chatRoom) return;
        if (this.chatRoom != null) {
            this.chatRoom.getMessages().remove(this);
        }
        this.chatRoom = chatRoom;
        if (chatRoom != null && !chatRoom.getMessages().contains(this)) {
            chatRoom.getMessages().add(this);
        }
    }

    /** 발신자 설정 (Member → 메시지 목록은 조회하지 않으므로 단방향 설정만) */
    public void setSender(Member sender) {
        this.sender = sender;
    }
}
