package com.example.demo.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.repository.UserSessionRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PresenceTimeoutScheduler {

	@Autowired
    private UserSessionRepository userSessionRepository;

    @Scheduled(fixedDelay = 60000)
    public void refreshPresenceState() {
        // Mark users offline if heartbeat has expired.
    }
}

