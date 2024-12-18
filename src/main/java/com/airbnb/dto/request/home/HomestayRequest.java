package com.airbnb.dto.request.home;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class HomestayRequest {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "host_id")
    private Long hostId;

    @Column(name = "type")
    private String type;

    @Column(name = "images")
    private String images;

    @Column(name = "guests")
    private Short guests;

    @Column(name = "bedrooms")
    private Short bedrooms;

    @Column(name = "bathrooms")
    private Short bathrooms;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "phone")
    private String phone;
}