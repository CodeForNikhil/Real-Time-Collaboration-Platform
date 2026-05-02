package com.example.demo.dto.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class WebSocketSessionRegistry {

	private final Map<Long, String> userSessionMap = new ConcurrentHashMap<>();

    public void register(Long userId, String sessionId) {
        userSessionMap.put(userId, sessionId);
    }

    public void unregister(Long userId) {
        userSessionMap.remove(userId);
    }

    public String getSessionId(Long userId) {
        return userSessionMap.get(userId);
    }

    public boolean isConnected(Long userId) {
        return userSessionMap.containsKey(userId);
    }

}
