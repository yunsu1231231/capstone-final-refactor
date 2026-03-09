package com.example.fitmotion.chatmessage.service;

import com.example.fitmotion.chatmessage.entity.ChatMessage;
import com.example.fitmotion.chatmessage.converter.ChatMessageConverter;
import com.example.fitmotion.chatmessage.dto.ChatMessageResponseDto;
import com.example.fitmotion.chatmessage.repository.ChatMessageRepository;
import com.example.fitmotion.chatroom.entity.ChatRoom;
import com.example.fitmotion.chatroom.service.ChatRoomService;
import com.example.fitmotion.member.entity.Member;
import com.example.fitmotion.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;
    private final MemberRepository memberRepository;
    private final ChatMessageConverter converter;

    @Override
    @Transactional
    public ChatMessageResponseDto sendMessage(Long chatRoomId, Long senderId, String content) {
        ChatRoom room = chatRoomService.getEntity(chatRoomId);
        Member sender = memberRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. id=" + senderId));

        ChatMessage message = ChatMessage.builder()
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();
        message.setSender(sender);
        room.addMessage(message);

        ChatMessage saved = chatMessageRepository.save(message);
        return converter.toResponseDto(saved);
    }

    @Override
    public List<ChatMessageResponseDto> findByChatRoomId(Long chatRoomId) {
        return chatMessageRepository.findByChatRoomIdOrderByCreatedAtAsc(chatRoomId).stream()
                .map(converter::toResponseDto)
                .toList();
    }
}
