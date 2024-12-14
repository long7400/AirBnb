package com.airbnb.mapper.booking;

import com.airbnb.dto.response.booking.BookingResponse;
import com.airbnb.entity.Booking;

import java.util.List;

public interface BookingMapper {
    BookingResponse toResponse(Booking booking);

    List<BookingResponse> toListResponse(List<Booking> booking);
}