package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class OnlineUserResponse {

	private Long id;
    private String fullName;
    private String role;
    private boolean online;
	public OnlineUserResponse(Long id, String fullName, String role, boolean online) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.role = role;
		this.online = online;
	}
	public OnlineUserResponse() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	@Override
	public String toString() {
		return "OnlineUserResponse [id=" + id + ", fullName=" + fullName + ", role=" + role + ", online=" + online
				+ "]";
	}
    
    
    
}

