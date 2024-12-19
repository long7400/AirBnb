package com.airbnb.dto.request.booking;

import com.airbnb.annotation.CheckDates;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@CheckDates
@Data
@Builder
public class BookingRequest {

    @NotBlank(message = "Request ID cannot be blank")
    private String requestId;

    @NotNull(message = "User ID must not be null")
    private Long userId;

    @NotNull(message = "Homestay ID must not be null")
    private Long homestayId;

    @NotNull(message = "Check-in date must not be null")
    private LocalDate checkinDate;

    @NotNull(message = "Check-out date must not be null")
    private LocalDate checkoutDate;

    @Positive(message = "Number of guests must be a positive value")
    private Short guests;
}