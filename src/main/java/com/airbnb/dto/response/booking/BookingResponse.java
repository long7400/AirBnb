package com.airbnb.dto.response.booking;

import com.airbnb.enums.BookingStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingResponse {

    @Column(name = "id")
    private Long id;

    @Column(name = "checkin_date")
    private LocalDate checkinDate;

    @Column(name = "checkout_date")
    private LocalDate checkoutDate;

    @Column(name = "guests")
    private Short guests;

    @Column(name = "status")
    private BookingStatus status;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "note")
    private String note;
}