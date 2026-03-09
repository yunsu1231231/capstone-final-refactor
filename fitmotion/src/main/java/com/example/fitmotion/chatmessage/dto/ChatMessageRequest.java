package com.example.fitmotion.chatmessage.dto;

/** WebSocket 채팅 전송 요청 (memberId 있으면 DB 저장) */
public record ChatMessageRequest(String username, String content, Long memberId) {
}
