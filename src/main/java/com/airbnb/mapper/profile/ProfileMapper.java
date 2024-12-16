package com.airbnb.mapper.profile;

import com.airbnb.dto.response.profile.ProfileResponse;
import com.airbnb.entity.Profile;

public interface ProfileMapper {
    ProfileResponse toReponse(Profile profile);
}