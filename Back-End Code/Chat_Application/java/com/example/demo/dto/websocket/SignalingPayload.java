package com.example.demo.dto.websocket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignalingPayload {
    private Long callerUserId;
    private Long targetUserId;
    private String type;
    private String payload;
	public SignalingPayload(Long callerUserId, Long targetUserId, String type, String payload) {
		super();
		this.callerUserId = callerUserId;
		this.targetUserId = targetUserId;
		this.type = type;
		this.payload = payload;
	}
	public SignalingPayload() {
		super();
	}
	public Long getCallerUserId() {
		return callerUserId;
	}
	public void setCallerUserId(Long callerUserId) {
		this.callerUserId = callerUserId;
	}
	public Long getTargetUserId() {
		return targetUserId;
	}
	public void setTargetUserId(Long targetUserId) {
		this.targetUserId = targetUserId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "SignalingPayload [callerUserId=" + callerUserId + ", targetUserId=" + targetUserId + ", type=" + type
				+ ", payload=" + payload + "]";
	}
    
    
}
