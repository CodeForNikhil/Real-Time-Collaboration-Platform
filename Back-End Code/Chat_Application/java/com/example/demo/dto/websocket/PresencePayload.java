package com.example.demo.dto.websocket;

public class PresencePayload {
    private Long userId;
    private String fullName;
    private String role;
    private String status;
    

    public PresencePayload() {
    }

    public PresencePayload(Long userId, String status) {
        this.userId = userId;
        this.status = status;
    }

    
	public PresencePayload(Long userId, String fullName, String role, String status) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.role = role;
		this.status = status;
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


	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "PresencePayload [userId=" + userId + ", fullName=" + fullName + ", role=" + role + ", status=" + status
				+ "]";
	}
    
    
}

