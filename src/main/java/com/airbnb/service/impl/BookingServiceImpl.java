package com.airbnb.service.impl;

import com.airbnb.dto.request.booking.BookingRequest;
import com.airbnb.dto.response.booking.BookingResponse;
import com.airbnb.dto.wrapper.TotalAmountWrapper;
import com.airbnb.entity.Booking;
import com.airbnb.entity.Homestay;
import com.airbnb.entity.HomestayAvailability;
import com.airbnb.entity.User;
import com.airbnb.enums.AvailabilityStatus;
import com.airbnb.enums.BookingStatus;
import com.airbnb.enums.HomestayStatus;
import com.airbnb.mapper.booking.BookingMapper;
import com.airbnb.repository.BookingRepository;
import com.airbnb.repository.HomestayAvailabilityRepository;
import com.airbnb.repository.HomestayRepository;
import com.airbnb.repository.UserRepository;
import com.airbnb.service.BookingService;
import com.airbnb.util.DateTimeUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    @Resource
    private HomestayRepository homestayRepository;

    @Resource
    private BookingRepository bookingRepository;

    @Resource
    private HomestayAvailabilityRepository homestayAvailabilityRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private BookingMapper bookingMapper;

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        log.info("Creating booking for requestId: {}", bookingRequest.getRequestId());
        TotalAmountWrapper totalAmountWrapper = new TotalAmountWrapper();
        Homestay homestay = fetchAndValidateHomestay(bookingRequest.getHomestayId(), bookingRequest);

        User user = fetchAndValidateUser(bookingRequest.getUserId());

        validateHomestayAvailability(bookingRequest, totalAmountWrapper);

        Booking booking = bookingMapper.buildBooking(bookingRequest, homestay, user);
        booking.setTotalAmount(totalAmountWrapper.getTotalAmount());
        Booking savedBooking = bookingRepository.save(booking);

        BookingResponse bookingResponse = bookingMapper.toResponse(booking);
        log.info("Booking created successfully with ID: {}", savedBooking.getId());
        return bookingResponse;
    }

    /**
     * Fetches the homestay by its ID, ensures it has not been marked as deleted,
     * and validates its eligibility based on the given booking request.
     *
     * @throws IllegalArgumentException if the homestay does not exist or has been deleted
     */
    private Homestay fetchAndValidateHomestay(Long homestayId, BookingRequest bookingRequest) {
        Optional<Homestay> homestayOpt = homestayRepository.findByIdAndNotDelete(homestayId);
        if (homestayOpt.isEmpty()) {
            throw new IllegalArgumentException(
                    "The homestay with ID " + homestayId + " does not exist or was deleted."
            );
        }
        Homestay homestay = homestayOpt.get();
        validateHomestayEligibility(homestay, bookingRequest);
        return homestay;
    }

    /**
     * Fetches a user by their ID and validates their existence.
     */
    private User fetchAndValidateUser(Long userId) {
        Optional<User> userOpt = userRepository.findUserById(userId);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist or could not be found.");
        }
        return userOpt.get();
    }

    /**
     * Validates the eligibility of a homestay for a booking request.
     * Ensures that the homestay is active and can accommodate the requested number of guests.
     */
    private void validateHomestayEligibility(Homestay homestay, BookingRequest bookingRequest) {
        if (homestay.getStatus() != HomestayStatus.ACTIVE) {
            throw new IllegalArgumentException(
                    "The homestay with ID " + bookingRequest.getHomestayId() + " is not active and cannot be booked at this time."
            );
        }
        if (homestay.getGuests() < bookingRequest.getGuests()) {
            throw new IllegalArgumentException(
                    "The homestay with ID " + bookingRequest.getHomestayId() +
                            " cannot accommodate the requested number of guests (" + bookingRequest.getGuests() + "). " +
                            "Maximum allowed guests: " + homestay.getGuests() + "."
            );
        }
    }

    /**
     * Validates the availability of a homestay for the requested booking dates.
     * Ensures that the booking does not exceed the maximum allowed duration
     * and checks if the homestay is available for the entire period.
     */
    private void validateHomestayAvailability(BookingRequest bookingRequest, TotalAmountWrapper totalAmountWrapper) {
        final int nights = (int) ChronoUnit.DAYS.between(bookingRequest.getCheckinDate(), bookingRequest.getCheckoutDate());
        if (nights > 365) {
            throw new IllegalArgumentException(
                    "Booking duration exceeds the maximum allowed limit of 365 nights. " +
                            "Please reduce the length of your stay for the homestay with ID " + bookingRequest.getHomestayId() + "."
            );
        }
        List<HomestayAvailability> existingBookings = homestayAvailabilityRepository.findAndLockHomestayAvailability(
                bookingRequest.getHomestayId(),
                String.valueOf(AvailabilityStatus.AVAILABLE),
                bookingRequest.getCheckinDate(),
                bookingRequest.getCheckoutDate()
        );
        if (existingBookings.isEmpty() || existingBookings.size() < 365) {
            throw new IllegalArgumentException(
                    "The homestay is not available between " + bookingRequest.getCheckinDate() +
                            " and " + bookingRequest.getCheckoutDate() + "."
            );
        }

        BigDecimal totalAmount = existingBookings.stream()
                .map(HomestayAvailability::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        totalAmountWrapper.setTotalAmount(totalAmount);

        log.info("The homestay with ID {} is available for the requested dates: {} to {}.",
                bookingRequest.getHomestayId(),
                bookingRequest.getCheckinDate(),
                bookingRequest.getCheckoutDate());
    }
}