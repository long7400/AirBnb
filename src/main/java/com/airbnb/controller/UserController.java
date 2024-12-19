package com.airbnb.controller;

import com.airbnb.dto.request.user.ChangePassRequest;
import com.airbnb.dto.request.user.LoginRequest;
import com.airbnb.dto.request.user.UserRegistrationRequest;
import com.airbnb.dto.response.common.CommonResponse;
import com.airbnb.dto.response.common.ResponseCode;
import com.airbnb.dto.response.user.UserResponse;
import com.airbnb.entity.User;
import com.airbnb.mapper.common.CommonMapper;
import com.airbnb.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private CommonMapper commonMapper;

    @PostMapping("/register")
    public CommonResponse<String> registerUser(@Validated @RequestBody UserRegistrationRequest request) {
        log.info(" ===== Start API register user ===== ");
        userService.registerUser(request);
        log.info("User [{}] registered successfully.", request.getUsername());
        return commonMapper.mapToCommonResponse(ResponseCode.CREATED, "User " + request.getUsername() + " registered successfully.");
    }

    @PostMapping("/login")
    public CommonResponse<String> login(@RequestBody LoginRequest loginRequest) {
        log.info(" ===== Start API login user ===== ");
        String token = userService.login(loginRequest);
        log.info("User [{}] login successfully.", loginRequest.getUsername());
        return commonMapper.mapToCommonResponse(ResponseCode.SUCCESS, token);
    }

    @GetMapping("/detail")
    public CommonResponse<UserResponse> getUserById(@RequestParam Long userId) {
        log.info(" ===== Start API get user by ID ===== ");
        UserResponse user = userService.getUserById(userId);
        log.info("Get user [{}] successfully.", userId);
        return commonMapper.mapToCommonResponse(ResponseCode.SUCCESS, user);
    }

    @GetMapping
    public CommonResponse<List<User>> getAll() {
        log.info(" ===== Start API get all user ===== ");
        List<User> users = userService.getUsers();
        return commonMapper.mapToCommonResponse(ResponseCode.CREATED, users);
    }

    @DeleteMapping("/delete")
    public CommonResponse<String> deleteUser(@RequestParam Long userId) {
        log.info(" ===== Start API delete user with ID: {} ===== ", userId);
        boolean isDelete = userService.deleteUser(userId);
        if (isDelete) {
            return commonMapper.mapToCommonResponse(ResponseCode.SUCCESS, "User with ID " + userId + " deleted successfully.");
        }
        return commonMapper.mapToCommonResponse(ResponseCode.BUSINESS_ERROR, "User with ID " + userId + " deleted failed.");
    }

    @PutMapping("/{userId}/change-password")
    public CommonResponse<String> changePassword(@PathVariable Long userId,
                                                 @RequestBody ChangePassRequest request) {
        log.info(" ===== Start API change password for user ID: {} ===== ", userId);
        userService.changePassword(userId, request);
        log.info("Password for user [{}] updated successfully.", userId);
        return commonMapper.mapToCommonResponse(ResponseCode.SUCCESS, "Password updated successfully for user ID " + userId);
    }
}