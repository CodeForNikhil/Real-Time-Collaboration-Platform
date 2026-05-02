package com.example.demo.service;

import com.example.demo.dto.websocket.PresencePayload;

public interface PresenceService {
	
	public void markUserOnline(Long userId);
	public void markUserOffline(Long userId);
	public void processPresenceUpdate(PresencePayload payload);
	 void clearUserSession(Long userId);


}
