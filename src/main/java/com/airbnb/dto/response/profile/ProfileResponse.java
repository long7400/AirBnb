package com.airbnb.dto.response.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileResponse {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "work")
    private String work;

    @Column(name = "about")
    private String about;

    @Column(name = "interests")
    private List<String> interests;

    @Column(name = "status")
    private String status;

    @Column(name = "extra_data")
    private List<String> extraData;
}