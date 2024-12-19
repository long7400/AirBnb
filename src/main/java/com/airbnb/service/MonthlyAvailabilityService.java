package com.airbnb.service;

import com.airbnb.dto.request.home.HomestayRequest;
import com.airbnb.entity.Homestay;

public interface MonthlyAvailabilityService {

    void saveMonthlyAvailability(Homestay homestay, HomestayRequest request);
}