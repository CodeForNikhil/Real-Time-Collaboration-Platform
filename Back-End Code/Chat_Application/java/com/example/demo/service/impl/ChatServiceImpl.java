package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.response.MessageResponse;
import com.example.demo.dto.websocket.ChatMessagePayload;
import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.kafka.event.ChatEvent;
import com.example.demo.kafka.producer.ChatEventProducer;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ChatService;
import com.example.demo.util.MapperUtil;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	private MessageRepository messageRepository;
    @Autowired
	private ChatEventProducer chatEventProducer;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public void processIncomingMessage(ChatMessagePayload payload) {
    	
    	System.out.println("Sender ID: " + payload.getSenderId());
        System.out.println("Receiver ID: " + payload.getReceiverId());
        System.out.println("Content: " + payload.getContent());

        
        

        Message message = new Message(
                payload.getSenderId(),
                payload.getReceiverId(),
                payload.getContent(),
                payload.getMessageType(),
                true,
                false
        );
               
        messageRepository.save(message);

        messagingTemplate.convertAndSend(
                "/topic/messages/" + payload.getReceiverId(),
                payload
        );

        messagingTemplate.convertAndSend(
                "/topic/messages/" + payload.getSenderId(),
                payload
        );

        chatEventProducer.publish(
                new ChatEvent(
                        payload.getSenderId(),
                        payload.getReceiverId(),
                        payload.getContent()
                )
        );
        }

    @Override
    public List<MessageResponse> getHistory(Long otherUserId) {
    	String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Current user not found"));

        return messageRepository.findConversation(currentUser.getId(), otherUserId)
                .stream()
                .map(MapperUtil::toMessageResponse)
                .toList();
    }


}
