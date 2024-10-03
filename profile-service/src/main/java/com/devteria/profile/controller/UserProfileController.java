package com.devteria.profile.controller;

import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.request.ProfileUpdateRequest;
import com.devteria.profile.dto.response.UserProfileResponse;
import com.devteria.profile.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE ,makeFinal = true)
public class UserProfileController {
    UserProfileService userProfileService;

    @PostMapping("/users")
    UserProfileResponse createProfile(@RequestBody ProfileCreationRequest request){
       return userProfileService.createProfile(request);
    }
    @GetMapping("/users/{profileId}")
    UserProfileResponse getProfile(@PathVariable String profileId)
    {
        return userProfileService.getProfile(profileId);
    }
    @DeleteMapping("/users/{profileId}")
    String deleteProfile(@PathVariable String profileId)
    {
        return userProfileService.deleteProfile(profileId);
    }
    @PutMapping("/users/{profileId}")
    UserProfileResponse updateProfile(@RequestBody ProfileUpdateRequest request ,@PathVariable String profileId)
    {
        return userProfileService.updateUserProfile(request,profileId);
    }


}
