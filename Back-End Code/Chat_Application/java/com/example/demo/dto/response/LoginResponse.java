package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class LoginResponse {
    private String token;
    private Long userId;
    private String fullName;
    private String role;
	public LoginResponse(String token, Long userId, String fullName, String role) {
		super();
		this.token = token;
		this.userId = userId;
		this.fullName = fullName;
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	@Override
	public String toString() {
		return "LoginResponse [token=" + token + ", userId=" + userId + ", fullName=" + fullName + ", role=" + role
				+ "]";
	}
    
    
}

