package com.airbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ward")
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ward_name", nullable = false)
    private String wardName;

    @Column(name = "district_id")
    private Integer districtId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", insertable = false, updatable = false)
    private District district;

    @OneToMany(mappedBy = "wardId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Homestay> homestays;
}