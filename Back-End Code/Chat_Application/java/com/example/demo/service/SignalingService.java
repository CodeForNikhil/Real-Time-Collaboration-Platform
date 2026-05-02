package com.example.demo.service;

import com.example.demo.dto.websocket.SignalingPayload;

public interface SignalingService {
	
	void forwardSignal(SignalingPayload payload);

}
