package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "call_logs")
public class CallLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long callerId;
    private Long receiverId;
    private String callType;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
	public CallLog(Long id, Long callerId, Long receiverId, String callType, String status, LocalDateTime startTime,
			LocalDateTime endTime) {
		super();
		this.id = id;
		this.callerId = callerId;
		this.receiverId = receiverId;
		this.callType = callType;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public CallLog() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getCallType() {
		return callType;
	}
	public void setCallType(String callType) {
		this.callType = callType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "CallLog [id=" + id + ", callerId=" + callerId + ", receiverId=" + receiverId + ", callType=" + callType
				+ ", status=" + status + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
    
    
    
}

