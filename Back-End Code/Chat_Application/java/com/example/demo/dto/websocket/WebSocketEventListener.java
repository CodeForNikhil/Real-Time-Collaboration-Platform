package com.example.demo.dto.websocket;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.example.demo.service.PresenceService;

@Component
public class WebSocketEventListener {

	private PresenceService presenceService;

    @EventListener
    public void handleConnected(SessionConnectedEvent event) {
        // Resolve authenticated principal and mark online if required.
    }

    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event) {
        // Resolve principal and mark offline if needed.
    }

}
