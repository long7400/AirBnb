package com.airbnb.mapper.booking.impl;

import com.airbnb.dto.request.booking.BookingRequest;
import com.airbnb.dto.response.booking.BookingResponse;
import com.airbnb.entity.Booking;
import com.airbnb.entity.Homestay;
import com.airbnb.entity.User;
import com.airbnb.enums.BookingStatus;
import com.airbnb.mapper.booking.BookingMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingResponse toResponse(Booking booking) {
        if (Objects.isNull(booking)) {
            return new BookingResponse();
        }
        return BookingResponse.builder()
                .id(booking.getId())
                .checkinDate(booking.getCheckinDate())
                .checkoutDate(booking.getCheckoutDate())
                .guests(booking.getGuests())
                .status(booking.getStatus())
                .subtotal(booking.getSubtotal())
                .totalAmount(booking.getTotalAmount())
                .requestId(booking.getRequestId())
                .build();
    }

    @Override
    public List<BookingResponse> toListResponse(List<Booking> bookings) {
        if (CollectionUtils.isEmpty(bookings)) {
            return Collections.emptyList();
        }
        return bookings.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Booking buildBooking(BookingRequest bookingRequest, Homestay homestay, User user) {
        Booking booking = new Booking();
        booking.setRequestId(bookingRequest.getRequestId());
        booking.setUser(user);
        booking.setHomestay(homestay);
        booking.setCheckinDate(bookingRequest.getCheckinDate());
        booking.setCheckoutDate(bookingRequest.getCheckoutDate());
        booking.setGuests(bookingRequest.getGuests());
        booking.setStatus(BookingStatus.PENDING);
        booking.setIsDelete(Boolean.FALSE);
        booking.setRequestId(String.valueOf(UUID.randomUUID()));
        return booking;
    }
}