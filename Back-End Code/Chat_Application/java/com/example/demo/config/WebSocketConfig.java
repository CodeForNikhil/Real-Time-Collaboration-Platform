package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws")
				.setAllowedOriginPatterns("*");
	}
	
	public void configureMessageBroker(MessageBrokerRegistry registry) {
			registry.setApplicationDestinationPrefixes("/app");
			registry.enableSimpleBroker("/topic","/queue");
			registry.setUserDestinationPrefix("/user");
	}
	
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
	    registration.interceptors(new ChannelInterceptor() {
	        @Override
	        public Message<?> preSend(Message<?> message, MessageChannel channel) {
	            System.out.println("Inbound STOMP: " + message);
	            return message;
	        }
	    });
	}
	
}
