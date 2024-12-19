package com.airbnb.mapper.booking;

import com.airbnb.dto.request.booking.BookingRequest;
import com.airbnb.dto.response.booking.BookingResponse;
import com.airbnb.entity.Booking;
import com.airbnb.entity.Homestay;
import com.airbnb.entity.User;

import java.util.List;

public interface BookingMapper {
    BookingResponse toResponse(Booking booking);

    List<BookingResponse> toListResponse(List<Booking> booking);

    Booking buildBooking(BookingRequest bookingRequest, Homestay homestay, User user);

}