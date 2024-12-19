package com.airbnb.service.impl;

import com.airbnb.dto.request.home.HomestayRequest;
import com.airbnb.dto.request.home.HomestaySearchRequest;
import com.airbnb.dto.response.home.HomestayDTO;
import com.airbnb.dto.response.home.HomestayResponse;
import com.airbnb.entity.Homestay;
import com.airbnb.entity.User;
import com.airbnb.enums.AvailabilityStatus;
import com.airbnb.exception.UserNotFoundException;
import com.airbnb.mapper.home.HomestayMapper;
import com.airbnb.repository.HomestayRepository;
import com.airbnb.repository.UserRepository;
import com.airbnb.service.HomestayService;
import com.airbnb.service.MonthlyAvailabilityService;
import com.airbnb.util.AuthUtil;
import com.airbnb.util.DateTimeUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class HomestayServiceImpl implements HomestayService {


    @Resource
    private UserRepository userRepository;

    @Resource
    private HomestayRepository homestayRepository;

    @Resource
    private MonthlyAvailabilityService monthlyAvailabilityService;

    @Resource
    private HomestayMapper homestayMapper;

    @Override
    @Transactional
    public boolean createHomestay(HomestayRequest request) throws Exception {
        log.info("Starting the process to create a homestay with request: {}", request);
        try {

            Long ownerId = AuthUtil.getCurrentUserId();
            log.info("Fetched owner ID: {}", ownerId);

            User owner = userRepository.findById(ownerId)
                    .orElseThrow(() -> {
                        log.error("Failed to find owner with ID: {}", ownerId);
                        return new UserNotFoundException("Owner not found with ID: " + ownerId);
                    });

            Homestay homestay = homestayMapper.toEntity(request);
            homestay.setOwner(owner);
            homestayRepository.save(homestay);
            log.info("Homestay saved successfully with ID: {}", homestay.getId());

            monthlyAvailabilityService.saveMonthlyAvailability(homestay, request);
            log.info("Monthly availability saved successfully.");
            return true;
        } catch (UserNotFoundException e) {
            log.error("UserNotFoundException occurred: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public List<HomestayDTO> searchHomestays(HomestaySearchRequest request) {
        request.setStatus(String.valueOf(AvailabilityStatus.AVAILABLE));

        LocalDate checkinDate = request.getCheckinDate();
        LocalDate checkoutDate = request.getCheckoutDate();

        int nights = (int) DateTimeUtils.getDiffInDays(checkinDate, checkoutDate);
        checkoutDate = checkoutDate.minusDays(1);

        return homestayRepository.searchHomestay(
                request.getLongitude(),
                request.getLatitude(),
                request.getRadius(),
                checkinDate,
                checkoutDate,
                nights,
                request.getGuests(),
                String.valueOf(AvailabilityStatus.AVAILABLE));
    }
}