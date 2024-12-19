package com.airbnb.service.impl;

import com.airbnb.dto.request.home.HomestayRequest;
import com.airbnb.entity.Homestay;
import com.airbnb.entity.HomestayAvailability;
import com.airbnb.enums.AvailabilityStatus;
import com.airbnb.repository.HomestayAvailabilityRepository;
import com.airbnb.service.MonthlyAvailabilityService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MonthlyAvailabilityServiceImpl implements MonthlyAvailabilityService {

    @Resource
    private HomestayAvailabilityRepository homestayAvailabilityRepository;

    @Override
    public void saveMonthlyAvailability(Homestay homestay, HomestayRequest request) {
        LocalDate now = LocalDate.now();

        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = now.withDayOfMonth(now.lengthOfMonth());

        List<HomestayAvailability> availabilityList = new ArrayList<>();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            HomestayAvailability availability = new HomestayAvailability();
            availability.setHomestayId(homestay.getId());
            availability.setDate(date);
            availability.setPrice(request.getPrice());
            availability.setStatus(AvailabilityStatus.AVAILABLE);

            availabilityList.add(availability);
        }

        if (!availabilityList.isEmpty()) {
            homestayAvailabilityRepository.saveAll(availabilityList);
        }
    }
}