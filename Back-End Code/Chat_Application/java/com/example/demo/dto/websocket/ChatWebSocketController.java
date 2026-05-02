package com.example.demo.dto.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.service.ChatService;

@Controller
public class ChatWebSocketController {
	
	@Autowired
	private ChatService chatService;
	
	@MessageMapping("/chat.send")
	public void sendMessage(ChatMessagePayload payload) {
		System.out.println(payload.getContent());
		
		System.out.println("Chat payload received:");
	    System.out.println("Sender: " + payload.getSenderId());
	    System.out.println("Receiver: " + payload.getReceiverId());
	    System.out.println("Content: " + payload.getContent());
		chatService.processIncomingMessage(payload);
	}
	
	

}
