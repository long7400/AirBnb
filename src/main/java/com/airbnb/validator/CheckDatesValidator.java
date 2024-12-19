package com.airbnb.validator;

import com.airbnb.annotation.CheckDates;
import com.airbnb.dto.request.booking.BookingRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class CheckDatesValidator implements ConstraintValidator<CheckDates, BookingRequest> {

    @Override
    public boolean isValid(BookingRequest request, ConstraintValidatorContext context) {
        if (request.getCheckinDate() == null || request.getCheckoutDate() == null) {
            return true;
        }

        LocalDate today = LocalDate.now();

        if (request.getCheckinDate().isBefore(today)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Check-in date must not be in the past")
                    .addPropertyNode("checkinDate")
                    .addConstraintViolation();
            return false;
        }

        if (request.getCheckoutDate().isBefore(today)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Check-out date must not be in the past")
                    .addPropertyNode("checkoutDate")
                    .addConstraintViolation();
            return false;
        }

        if (!request.getCheckoutDate().isAfter(request.getCheckinDate())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Check-out date must be after check-in date")
                    .addPropertyNode("checkoutDate")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}