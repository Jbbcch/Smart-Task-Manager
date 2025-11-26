package com.jbbcch.smarttaskmanager.user.mapper;

import com.jbbcch.smarttaskmanager.user.dto.UserRequest;
import com.jbbcch.smarttaskmanager.user.dto.UserResponse;
import com.jbbcch.smarttaskmanager.user.model.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserResponse userToUserResponse(User user);
    User userRequestToUser(UserRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
                 ignoreUnmappedSourceProperties = {"password"})
    void updateUserFromRequest(UserRequest request, @MappingTarget User user);
}
