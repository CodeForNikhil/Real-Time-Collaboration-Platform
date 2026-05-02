package com.example.demo.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demo.kafka.event.CallEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CallEventConsumer {

    @KafkaListener(topics = "call-events", groupId = "collab-group")
    public void listen(CallEvent event) {
        System.out.printf("Consumed call event: {}"+ event);
    }
}

