package com.airbnb.service;

import com.airbnb.dto.request.user.LoginRequest;
import com.airbnb.dto.request.user.UserRegistrationRequest;
import com.airbnb.dto.response.user.UserResponse;
import com.airbnb.entity.User;

import java.util.List;

public interface UserService {
    void registerUser(UserRegistrationRequest request);

    String login(LoginRequest loginRequest);

    UserResponse getUserById(Long userId);

    List<User> getUsers();
}