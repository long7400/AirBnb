package com.airbnb.mapper.impl;

import com.airbnb.dto.request.user.UserRegistrationRequest;
import com.airbnb.entity.User;
import com.airbnb.enums.UserStatus;
import com.airbnb.enums.UserType;
import com.airbnb.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserRegistrationRequest request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .type(UserType.GUEST)
                .status(UserStatus.ACTIVE)
                .build();
    }
}
