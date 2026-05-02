package com.example.demo.service;

import com.example.demo.kafka.event.NotificationEvent;

public interface NotificationService {
	void handleNotificationEvent(NotificationEvent event);

}
