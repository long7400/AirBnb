package com.airbnb.entity;

import com.airbnb.enums.HomestayStatus;
import com.airbnb.enums.HomestayType;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Point;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ward_id", referencedColumnName = "id", nullable = false)
    private Ward ward;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "homestay_amenity",
            joinColumns = @JoinColumn(name = "homestay_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private List<Amenity> amenities;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "geom", columnDefinition = "geometry(Point, 3857)")
    private Point geom;
}