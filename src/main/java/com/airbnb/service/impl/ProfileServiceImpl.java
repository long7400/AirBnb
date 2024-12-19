package com.airbnb.service.impl;

import com.airbnb.dto.request.profile.ProfileRequest;
import com.airbnb.entity.Profile;
import com.airbnb.enums.ProfileStatus;
import com.airbnb.repository.ProfileRepository;
import com.airbnb.service.ProfileService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.airbnb.util.HelpUtils.updateFieldIfNotNull;

@Slf4j
@Service
public class ProfileServiceImpl implements ProfileService {

    @Resource
    private ProfileRepository profileRepository;

    @Transactional
    public boolean createOrUpdateProfile(ProfileRequest request) {
        try {
            Optional<Profile> optionalProfile = profileRepository.findById(request.getUserId());

            Profile profile = optionalProfile.orElseGet(() -> {
                Profile newProfile = new Profile();
                newProfile.setUserId(request.getUserId());
                newProfile.setVersion(1L);
                newProfile.setStatus(ProfileStatus.ACTIVE);
                return newProfile;
            });


            updateFieldIfNotNull(request.getAvatar(), profile::setAvatar);
            updateFieldIfNotNull(request.getWork(), profile::setWork);
            updateFieldIfNotNull(request.getAbout(), profile::setAbout);
            updateFieldIfNotNull(request.getInterests(), profile::setInterests);
            updateFieldIfNotNull(request.getExtraData(), profile::setExtraData);
            if (optionalProfile.isPresent()) {
                profile.setVersion(profile.getVersion() + 1);
            }
            profileRepository.save(profile);
            return true;
        } catch (Exception e) {
            log.error("Error occurred while creating/updating profile for user ID: {}", request.getUserId(), e);
            return false;
        }
    }
}