package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.response.MessageResponse;
import com.example.demo.dto.websocket.ChatMessagePayload;

public interface ChatService {
	
	void processIncomingMessage(ChatMessagePayload payload);
    List<MessageResponse> getHistory(Long otherUserId);


}
