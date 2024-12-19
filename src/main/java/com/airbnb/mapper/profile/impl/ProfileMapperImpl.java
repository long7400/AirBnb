package com.airbnb.mapper.profile.impl;

import com.airbnb.dto.response.profile.ProfileResponse;
import com.airbnb.entity.Profile;
import com.airbnb.mapper.profile.ProfileMapper;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapperImpl implements ProfileMapper {
    @Override
    public ProfileResponse toReponse(Profile profile) {
        if (profile == null) {
            return null;
        }

        return ProfileResponse.builder()
                .userId(profile.getUserId())
                .avatar(profile.getAvatar())
                .work(profile.getWork())
                .about(profile.getAbout())
                .interests(profile.getInterests())
                .status(profile.getStatus() != null ? profile.getStatus().toString() : null)
                .extraData(profile.getExtraData())
                .build();
    }
}