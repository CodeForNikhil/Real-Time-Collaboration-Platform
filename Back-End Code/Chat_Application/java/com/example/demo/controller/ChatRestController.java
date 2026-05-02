package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.response.MessageResponse;
import com.example.demo.service.ChatService;

@RestController()
@RequestMapping("/api/chat")
public class ChatRestController {

	@Autowired
	private  ChatService chatService;
	
	@GetMapping("/history/{userId}")
	public List<MessageResponse> getHistory(@PathVariable Long userId){
		return chatService.getHistory(userId);
	}
	
	
}
