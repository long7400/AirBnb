package com.airbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "homestay_amenity")
@IdClass(HomestayAmenityId.class)
public class HomestayAmenity {
    @Id
    @Column(name = "homestay_id")
    private Long homestayId;

    @Id
    @Column(name = "amenity_id")
    private Integer amenityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "homestay_id", insertable = false, updatable = false)
    private Homestay homestay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amenity_id", insertable = false, updatable = false)
    private Amenity amenity;
}