package com.airbnb.controller;

import com.airbnb.dto.request.home.HomestayRequest;
import com.airbnb.dto.request.home.HomestaySearchRequest;
import com.airbnb.dto.response.common.CommonResponse;
import com.airbnb.dto.response.common.ResponseCode;
import com.airbnb.dto.response.home.HomestayDTO;
import com.airbnb.dto.response.home.HomestayResponse;
import com.airbnb.mapper.common.CommonMapper;
import com.airbnb.service.HomestayService;
import com.airbnb.util.DateTimeUtils;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/homestay")
public class UserHomestayController {

    @Resource
    private CommonMapper commonMapper;

    @Resource
    private HomestayService homestayService;

    @GetMapping("/search")
    public CommonResponse<List<HomestayDTO>> createHomestay(@RequestParam(value = "longitude") Double longitude,
                                                            @RequestParam(value = "latitude") Double latitude,
                                                            @RequestParam(value = "radius") Double radius,
                                                            @RequestParam(value = "checkin_date") String checkinDate,
                                                            @RequestParam(value = "checkout_date") String checkoutDate,
                                                            @RequestParam(value = "guests") Integer guests) {
        log.info("Process search homestay: ");

        HomestaySearchRequest request = HomestaySearchRequest.builder()
                .longitude(longitude)
                .latitude(latitude)
                .radius(radius)
                .checkinDate(DateTimeUtils.parse(checkinDate))
                .checkoutDate(DateTimeUtils.parse(checkoutDate))
                .guests(guests)
                .build();

        List<HomestayDTO> responseList = homestayService.searchHomestays(request);
        return commonMapper.mapToCommonResponse(ResponseCode.SUCCESS, responseList);
    }
}