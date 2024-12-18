package com.airbnb.mapper.home.impl;

import com.airbnb.dto.request.home.HomestayRequest;
import com.airbnb.entity.Homestay;
import com.airbnb.enums.HomestayStatus;
import com.airbnb.enums.HomestayType;
import com.airbnb.mapper.home.HomestayMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class HomestayMapperImpl implements HomestayMapper {

    @Override
    public Homestay toEntity(HomestayRequest request) {
        return Homestay.builder()
                .name(request.getName())
                .description(request.getDescription())
                .type(HomestayType.valueOf(request.getType()))
                .status(HomestayStatus.ACTIVE)
                .address(request.getAddress())
                .images(Collections.singletonList(request.getImages()))
                .guests(request.getGuests())
                .bedrooms(request.getBedrooms())
                .bathrooms(request.getBathrooms())
                .longitude(request.getLongitude())
                .latitude(request.getLatitude())
                .version(1L)
                .phoneNumber(request.getPhone())
                .isDelete(Boolean.FALSE)
                .build();
    }
}