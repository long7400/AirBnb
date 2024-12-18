package com.airbnb.dto.request.profile;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProfileRequest {

    @Column(name = "user_id")
    @NotNull(message = "User ID must not be null")
    private Long userId;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "work")
    private String work;

    @Column(name = "about")
    private String about;

    @Column(name = "interests")
    private List<String> interests;

    @Column(name = "extra_data")
    private List<String> extraData;
}