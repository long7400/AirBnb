package com.airbnb.controller;

import com.airbnb.dto.request.booking.BookingRequest;
import com.airbnb.dto.response.booking.BookingResponse;
import com.airbnb.dto.response.common.CommonResponse;
import com.airbnb.dto.response.common.ResponseCode;
import com.airbnb.mapper.common.CommonMapper;
import com.airbnb.service.BookingService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Resource
    private CommonMapper commonMapper;

    @Resource
    private BookingService bookingService;

    @PostMapping
    public CommonResponse<?> createBooking(@Valid @RequestBody BookingRequest bookingRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return commonMapper.mapToCommonResponse(ResponseCode.INTERNAL_SERVER_ERROR, bindingResult.getAllErrors());
        }
        BookingResponse response = bookingService.createBooking(bookingRequest);
        return commonMapper.mapToCommonResponse(ResponseCode.CREATED, response);
    }
}