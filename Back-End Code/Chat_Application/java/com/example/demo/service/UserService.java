package com.example.demo.service;

import com.example.demo.dto.response.UserResponse;

public interface UserService {
	
	UserResponse getCurrentUser();
    UserResponse getById(Long id);


}
