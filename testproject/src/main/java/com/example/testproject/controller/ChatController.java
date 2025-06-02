package com.example.testproject.controller;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.testproject.dto.ChatMessageDTO;
import com.example.testproject.entity.ChatMessage;
import com.example.testproject.entity.ChatRoom;
import com.example.testproject.entity.User;
import com.example.testproject.repository.ChatMessageRepository;
import com.example.testproject.repository.ChatRoomRepository;
import com.example.testproject.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    @MessageMapping("/chat.sendMessage.{roomId}")
    public void sendMessage(@DestinationVariable Long roomId, ChatMessageDTO messageDto) {
        ChatRoom room = chatRoomRepository.findById(roomId).orElseThrow();
        User sender = userRepository.findById(messageDto.getSender().getId()).orElseThrow();

        ChatMessage msg = new ChatMessage();
        msg.setChatRoom(room);
        msg.setSender(sender);
        msg.setContent(messageDto.getContent());
        msg.setTimestamp(LocalDateTime.now());
        chatMessageRepository.save(msg);

        messagingTemplate.convertAndSend("/topic/chat.room." + roomId, messageDto);
    }

    @GetMapping("/chat/room/{roomId}")
    public String getchatRoom(@PathVariable Long roomId, Model model) {
        model.addAttribute("roomId", roomId);
        return "chat";
    }

}