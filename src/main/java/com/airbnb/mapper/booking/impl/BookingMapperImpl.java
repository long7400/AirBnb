package com.airbnb.mapper.booking.impl;

import com.airbnb.dto.response.booking.BookingResponse;
import com.airbnb.entity.Booking;
import com.airbnb.mapper.booking.BookingMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
}