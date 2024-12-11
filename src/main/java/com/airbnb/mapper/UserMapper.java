package com.airbnb.mapper;

import com.airbnb.dto.request.user.UserRegistrationRequest;
import com.airbnb.entity.User;

public interface UserMapper {

    User toEntity(UserRegistrationRequest request);
}
