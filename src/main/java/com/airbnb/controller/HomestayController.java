package com.airbnb.controller;

import com.airbnb.annotation.HostOnly;
import com.airbnb.dto.request.home.HomestayRequest;
import com.airbnb.dto.response.common.CommonResponse;
import com.airbnb.dto.response.common.ResponseCode;
import com.airbnb.dto.response.home.HomestayResponse;
import com.airbnb.mapper.common.CommonMapper;
import com.airbnb.service.HomestayService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@HostOnly
@Slf4j
@RestController
@RequestMapping("/api/v1/host/homestay")
public class HomestayController {

    @Resource
    private CommonMapper commonMapper;

    @Resource
    private HomestayService homestayService;

    @PostMapping
    public CommonResponse<String> createHomestay(@RequestBody HomestayRequest request) throws Exception {
        log.info("Creating homestay: {}", request);
        boolean isSucc = homestayService.createHomestay(request);
        if (isSucc) {
            return commonMapper.mapToCommonResponse(ResponseCode.CREATED, null);
        }
        return commonMapper.mapToCommonResponse(ResponseCode.INTERNAL_SERVER_ERROR, null);
    }

    @GetMapping("/{id}")
    public CommonResponse<HomestayResponse> getHomestayById(@PathVariable Long id) {
        log.info("Fetching homestay with ID: {}", id);
        return commonMapper.mapToCommonResponse(ResponseCode.SUCCESS, null);
    }

    @PutMapping("/{id}")
    public CommonResponse<HomestayResponse> updateHomestay(@PathVariable Long id, @RequestBody HomestayRequest homestayDetails) {
        log.info("Updating homestay with ID: {}", id);
        return commonMapper.mapToCommonResponse(ResponseCode.SUCCESS, null);
    }

    @DeleteMapping("/{id}")
    public CommonResponse<Map<String, Boolean>> deleteHomestay(@PathVariable Long id) {
        log.info("Deleting homestay with ID: {}", id);
        return commonMapper.mapToCommonResponse(ResponseCode.SUCCESS, null);
    }
}