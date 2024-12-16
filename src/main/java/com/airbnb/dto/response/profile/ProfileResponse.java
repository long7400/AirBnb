package com.airbnb.dto.response.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    private Long userId;
    private String avatar;
    private String work;
    private String about;
    private List<String> interests;
    private String status;
    private List<String> extraData;
}