package com.airbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "province_name", nullable = false)
    private String provinceName;

    @Column(name = "country_id")
    private Integer countryId;

    @OneToMany(mappedBy = "provinceId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<District> districts;
}