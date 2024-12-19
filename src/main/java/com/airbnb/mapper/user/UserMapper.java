package com.airbnb.mapper.user;

import com.airbnb.dto.request.user.UserRegistrationRequest;
import com.airbnb.dto.response.user.UserResponse;
import com.airbnb.entity.User;

public interface UserMapper {

    User toEntity(UserRegistrationRequest request);

    UserResponse toResponse(User user);
}
