package com.example.demo.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.kafka.event.NotificationEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationEventProducer {

	@Autowired
    private  KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(NotificationEvent event) {
        kafkaTemplate.send("notification-events", String.valueOf(event.getUserId()), event);
    }
}
