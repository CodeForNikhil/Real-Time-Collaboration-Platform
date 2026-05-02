package com.example.demo.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotificationEvent {
    private Long userId;
    private String type;
    private String message;
    
    
	public NotificationEvent(Long userId, String type, String message) {
		super();
		this.userId = userId;
		this.type = type;
		this.message = message;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "NotificationEvent [userId=" + userId + ", type=" + type + ", message=" + message + "]";
	}
    
    
    
}

