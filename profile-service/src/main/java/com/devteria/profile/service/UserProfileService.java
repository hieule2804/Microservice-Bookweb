package com.devteria.profile.service;

import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.request.ProfileUpdateRequest;
import com.devteria.profile.dto.response.UserProfileResponse;
import com.devteria.profile.entity.UserProfile;
import com.devteria.profile.mapper.UserProfileMapper;
import com.devteria.profile.repository.UserProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserProfileService {
    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;

   public UserProfileResponse createProfile(ProfileCreationRequest request)
    {
        UserProfile userProfile =userProfileMapper.toUserProfile(request);
        userProfile = userProfileRepository.save(userProfile);
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    public UserProfileResponse getProfile(String id)
    {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Profile not found!"));
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    public String deleteProfile(String id)
    {
        //find id
        if(userProfileRepository.findById(id).isPresent())
        {
            userProfileRepository.deleteById(id);
            return "Delete Succesfull!";
        }
        return "Not Found Profile Id";
    }

    public UserProfileResponse updateUserProfile(ProfileUpdateRequest request, String id) {
        Optional<UserProfile> existingProfileOpt = userProfileRepository.findById(id);
        if (existingProfileOpt.isEmpty()) {
            throw new RuntimeException("Profile not found!");
        }

        UserProfile existingProfile = existingProfileOpt.get();
        userProfileMapper.profileUpdatetoUserProfile(request, existingProfile);
        userProfileRepository.save(existingProfile);
        return userProfileMapper.toUserProfileResponse(existingProfile);
    }

}
