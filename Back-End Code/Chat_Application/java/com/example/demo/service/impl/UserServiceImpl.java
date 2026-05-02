package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.MapperUtil;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
    public UserResponse getCurrentUser() {
		String username = SecurityContextHolder.getContext()
	            .getAuthentication()
	            .getName();
			
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return MapperUtil.toUserResponse(user);
    }

    @Override
    public UserResponse getById(Long id) {
        return MapperUtil.toUserResponse(userRepository.findById(id).orElseThrow());
    }


}
