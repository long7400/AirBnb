package com.airbnb.service.impl;

import com.airbnb.dto.request.user.UserRegistrationRequest;
import com.airbnb.entity.User;
import com.airbnb.enums.UserStatus;
import com.airbnb.enums.UserType;
import com.airbnb.exception.DuplicateEntityException;
import com.airbnb.mapper.UserMapper;
import com.airbnb.repository.UserRepository;
import com.airbnb.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserMapper userMapper;

    @Override
    public void registerUser(UserRegistrationRequest request) {
        List<String> errors = validateUserRegistration(request);

        if (!errors.isEmpty()) {
            throw new DuplicateEntityException(errors);
        }

        User newUser = userMapper.toEntity(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        newUser.setPassword(encodedPassword);
        userRepository.save(newUser);


    }

    private List<String> validateUserRegistration(UserRegistrationRequest request) {
        List<String> errors = new ArrayList<>();

        if (userRepository.existsByUsername(request.getUsername())) {
            errors.add("Username already exists: " + request.getUsername());
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            errors.add("Email already exists: " + request.getEmail());
        }

        return errors;
    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public List<User> listUsers() {
        return List.of();
    }
}
