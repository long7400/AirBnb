package com.airbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "homestay_availability")
@IdClass(HomestayAvailabilityId.class)
public class HomestayAvailability {
    @Id
    @Column(name = "homestay_id")
    private Long homestayId;

    @Id
    private LocalDate date;

    private BigDecimal price;

    private Short status;
}