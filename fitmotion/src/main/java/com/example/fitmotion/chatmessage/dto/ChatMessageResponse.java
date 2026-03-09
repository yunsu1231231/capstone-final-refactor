package com.example.fitmotion.chatmessage.dto;

/** WebSocket 채팅 브로드캐스트 응답 */
public record ChatMessageResponse(String username, String content) {
}
