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
public class CallEvent {
    private Long callerId;
    private Long receiverId;
    private String type;
	public CallEvent(Long callerId, Long receiverId, String type) {
		super();
		this.callerId = callerId;
		this.receiverId = receiverId;
		this.type = type;
	}
	public CallEvent() {
		super();
	}
	public Long getCallerId() {
		return callerId;
	}
	public void setCallerId(Long callerId) {
		this.callerId = callerId;
	}
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "CallEvent [callerId=" + callerId + ", receiverId=" + receiverId + ", type=" + type + "]";
	}
    
    
}
