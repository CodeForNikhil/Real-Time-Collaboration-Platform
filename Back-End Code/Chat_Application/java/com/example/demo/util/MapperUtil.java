package com.example.demo.util;

import com.example.demo.dto.response.MessageResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.Message;
import com.example.demo.entity.User;

public class MapperUtil {
	
    private MapperUtil() {}

    public static UserResponse toUserResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getFullName(), user.getRole());
    }

    public static MessageResponse toMessageResponse(Message message) {
        return new MessageResponse(
                message.getId(),
                message.getSenderId(),
                message.getReceiverId(),
                message.getContent(),
                message.getMessageType(),
                message.isDelivered(),
                message.isReadFlag()
        );
    }


}
