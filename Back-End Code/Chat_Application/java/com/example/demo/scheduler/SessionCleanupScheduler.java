package com.example.demo.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.repository.UserSessionRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SessionCleanupScheduler {

	@Autowired
    private UserSessionRepository userSessionRepository;

    @Scheduled(fixedDelay = 300000)
    public void cleanupStaleSessions() {
        // Implement stale-session expiration logic based on lastSeen.
    }
}

