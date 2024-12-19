package com.airbnb.entity;

import com.airbnb.enums.ProfileStatus;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

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

    @Column(name = "interests", columnDefinition = "text[]")
    @Type(ListArrayType.class)
    private List<String> interests;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    @Column(name = "extra_data", columnDefinition = "text[]")
    @Type(ListArrayType.class)
    private List<String> extraData;

    private Long version;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}