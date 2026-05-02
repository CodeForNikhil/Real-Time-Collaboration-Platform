package com.example.demo.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demo.kafka.event.NotificationEvent;
import com.example.demo.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationEventConsumer {

	@Autowired
    private  NotificationService notificationService;

    @KafkaListener(topics = "notification-events", groupId = "collab-group")
    public void listen(NotificationEvent event) {
        notificationService.handleNotificationEvent(event);
    }
}

