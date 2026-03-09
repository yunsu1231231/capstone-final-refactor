package com.example.fitmotion.chatmessage.entity;

import com.example.fitmotion.chatroom.entity.ChatRoom;
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

    public void setSender(Member sender) {
        this.sender = sender;
    }
}
