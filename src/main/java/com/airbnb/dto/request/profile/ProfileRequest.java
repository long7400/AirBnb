package com.airbnb.dto.request.profile;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProfileRequest {

    @NotNull(message = "User ID must not be null")
    private Long userId;

    private String avatar;
    private String work;
    private String about;
    private List<String> interests;
    private List<String> extraData;
}