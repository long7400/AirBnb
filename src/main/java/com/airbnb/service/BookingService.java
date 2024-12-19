package com.airbnb.service;

import com.airbnb.dto.request.booking.BookingRequest;
import com.airbnb.dto.response.booking.BookingResponse;
import com.airbnb.entity.Booking;

public interface BookingService {

    BookingResponse createBooking(BookingRequest bookingRequest);
}