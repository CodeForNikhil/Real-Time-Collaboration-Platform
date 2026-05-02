package com.example.demo.dto.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.service.PresenceService;

@Controller
public class PresenceWebSocketController {

	@Autowired
	private PresenceService presenceService;
	
	
	@MessageMapping("/presence.update")
    public void updatePresence(PresencePayload payload) {
        presenceService.processPresenceUpdate(payload);
    }

}
