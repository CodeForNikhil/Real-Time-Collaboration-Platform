package com.example.demo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Notification;
import com.example.demo.kafka.event.NotificationEvent;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	private  NotificationRepository notificationRepository;

    @Override
    @Async("collabTaskExecutor")
    public void handleNotificationEvent(NotificationEvent event) {
        Notification notification = new Notification (
        		event.getUserId(), event.getType(), event.getMessage(), false);
        		
                
        notificationRepository.save(notification);
    }


}
