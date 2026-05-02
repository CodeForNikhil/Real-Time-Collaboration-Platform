package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String fullName;
    private String role;
    private boolean online;
    
	public UserResponse(Long id, String username, String fullName, String role, boolean online) {
		super();
		this.id = id;
		this.username = username;
		this.fullName = fullName;
		this.role = role;
		this.online = online;
	}
	
	public UserResponse(Long id2, String username2, String fullName2, String role2) {
		this.id = id2;
		this.username = username2;
		this.fullName = fullName2;
		this.role = role2;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
		return "UserResponse [id=" + id + ", username=" + username + ", fullName=" + fullName + ", role=" + role
				+ ", online=" + online + "]";
	}
	
	
    
    
}

