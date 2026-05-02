package com.example.demo.dto.websocket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessagePayload {
    private Long senderId;
    private Long receiverId;
    private String content;
    private String messageType;
	public ChatMessagePayload(Long senderId, Long receiverId, String content, String messageType) {
		super();
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.content = content;
		this.messageType = messageType;
	}
	public Long getSenderId() {
		return senderId;
	}
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	@Override
	public String toString() {
		return "ChatMessagePayload [senderId=" + senderId + ", receiverId=" + receiverId + ", content=" + content
				+ ", messageType=" + messageType + "]";
	}
    
    
}
