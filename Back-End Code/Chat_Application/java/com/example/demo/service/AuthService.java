package com.example.demo.service;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.LoginResponse;

public interface AuthService {
	
	public LoginResponse register(RegisterRequest request);
	public LoginResponse login(LoginRequest request);
	public void logoutCurrentUser();


}
