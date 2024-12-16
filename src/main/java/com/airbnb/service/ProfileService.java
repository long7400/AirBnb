package com.airbnb.service;

import com.airbnb.dto.request.profile.ProfileRequest;

public interface ProfileService {
    boolean createOrUpdateProfile(ProfileRequest request);
}