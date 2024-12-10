package com.airbnb.entity;

import com.airbnb.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class Profile extends BaseEntity {
    @Id
    @Column(name = "user_id")
    private Long userId;

    private String avatar;

    private String work;

    private String about;

    @Column(columnDefinition = "json")
    private String interests;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    @Column(name = "extra_data", columnDefinition = "json")
    private String extraData;

    private Long version;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}