package com.devteria.profile.mapper;

import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.request.ProfileUpdateRequest;
import com.devteria.profile.dto.response.UserProfileResponse;
import com.devteria.profile.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
  UserProfile toUserProfile(ProfileCreationRequest req);

  UserProfileResponse toUserProfileResponse(UserProfile entity);


  void profileUpdatetoUserProfile(ProfileUpdateRequest req, @MappingTarget UserProfile userProfile);
}
