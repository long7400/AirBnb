package com.airbnb.entity;

import com.airbnb.enums.HomestayStatus;
import com.airbnb.enums.HomestayType;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "homestay")
public class Homestay extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HomestayType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HomestayStatus status;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String address;

    @Column(name = "ward_id")
    private Integer wardId;

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "province_id")
    private Integer provinceId;

    @Column(name = "images", columnDefinition = "text[]")
    @Type(ListArrayType.class)
    private List<String> images;

    private Short guests;

    private Short bedrooms;

    private Short bathrooms;

    @Column(name = "extra_data", columnDefinition = "text[]")
    @Type(ListArrayType.class)
    private List<String> extraData;

    private Long version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", insertable = false, updatable = false)
    private User owner;

    @OneToMany(mappedBy = "homestay", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking> bookings;

    @ManyToMany
    @JoinTable(
            name = "homestay_amenity",
            joinColumns = @JoinColumn(name = "homestay_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private List<Amenity> amenities;
}