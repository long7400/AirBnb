package com.airbnb.controller;

import com.airbnb.dto.request.user.UserRegistrationRequest;
import com.airbnb.dto.response.common.CommonResponse;
import com.airbnb.dto.response.common.ResponseCode;
import com.airbnb.mapper.CommonMapper;
import com.airbnb.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private CommonMapper commonMapper;

    @PostMapping
    public CommonResponse<String> registerUser(@Validated @RequestBody UserRegistrationRequest request) {
        log.info(" ===== Start API register user ===== ");
        userService.registerUser(request);
        log.info("User [{}] registered successfully.", request.getUsername());
        return commonMapper.mapToCommonResponse(ResponseCode.CREATED, "User " + request.getUsername() + " registered successfully.");
    }
}