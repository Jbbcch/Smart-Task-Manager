package com.jbbcch.smarttaskmanager.user.api;

import com.jbbcch.smarttaskmanager.user.dto.UserRequest;
import com.jbbcch.smarttaskmanager.user.dto.UserResponse;

import java.util.UUID;

public interface UserInternalAPI {
    UserResponse createUser(UserRequest request);
    UserResponse getUserById(UUID id);
    UserResponse updateUserById(UUID id, UserRequest request);
    UserResponse deleteUserById(UUID id);
    UserResponse getCurrentUser();
}
