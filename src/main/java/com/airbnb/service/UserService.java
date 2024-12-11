package com.airbnb.service;

import com.airbnb.dto.request.user.UserRegistrationRequest;
import com.airbnb.entity.User;

import java.util.List;

public interface UserService {
    void registerUser(UserRegistrationRequest request);

    boolean login(String username, String password);

    User getUserById(Long userId);

    List<User> listUsers();
}
