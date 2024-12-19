package com.airbnb.controller;

import com.airbnb.dto.request.profile.ProfileRequest;
import com.airbnb.dto.response.common.CommonResponse;
import com.airbnb.dto.response.common.ResponseCode;
import com.airbnb.mapper.common.CommonMapper;
import com.airbnb.service.ProfileService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    @Resource
    private ProfileService profileService;

    @Resource
    private CommonMapper commonMapper;

    @PostMapping
    public CommonResponse<String> createOrUpdateProfile(@Validated @RequestBody ProfileRequest request) {
        log.info(" ===== Start API create or update profile ===== ");
        boolean isProcessed = profileService.createOrUpdateProfile(request);
        if (isProcessed) {
            return commonMapper.mapToCommonResponse(ResponseCode.SUCCESS, "Profile has been processed successfully.");
        } else {
            return commonMapper.mapToCommonResponse(ResponseCode.BUSINESS_ERROR, "Error occurred while processing the profile.");
        }
    }
}