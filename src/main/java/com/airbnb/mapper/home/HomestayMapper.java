package com.airbnb.mapper.home;

import com.airbnb.dto.request.home.HomestayRequest;
import com.airbnb.entity.Homestay;

public interface HomestayMapper {

    Homestay toEntity(HomestayRequest homestayRequest);
}
