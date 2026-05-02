package com.example.demo.service.impl;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dto.websocket.SignalingPayload;
import com.example.demo.kafka.event.CallEvent;
import com.example.demo.kafka.producer.CallEventProducer;
import com.example.demo.service.SignalingService;

@Service
public class SignalingServiceImpl implements SignalingService {

    private final SimpMessagingTemplate messagingTemplate;
    private final CallEventProducer callEventProducer;

    public SignalingServiceImpl(
            SimpMessagingTemplate messagingTemplate,
            CallEventProducer callEventProducer
    ) {
        this.messagingTemplate = messagingTemplate;
        this.callEventProducer = callEventProducer;
    }

    @Override
    public void forwardSignal(SignalingPayload payload) {

        System.out.println("Signal received in service");
        System.out.println("Caller: " + payload.getCallerUserId());
        System.out.println("Target: " + payload.getTargetUserId());
        System.out.println("Type: " + payload.getType());

        messagingTemplate.convertAndSend(
                "/topic/signaling/" + payload.getTargetUserId(),
                payload
        );

        callEventProducer.publish(
                new CallEvent(
                        payload.getCallerUserId(),
                        payload.getTargetUserId(),
                        payload.getType()
                )
        );
    }
}