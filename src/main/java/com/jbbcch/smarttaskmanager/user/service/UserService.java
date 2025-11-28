package com.jbbcch.smarttaskmanager.user.service;

import com.jbbcch.smarttaskmanager.department.api.external.DepartmentAssignmentExternalAPI;
import com.jbbcch.smarttaskmanager.security.core.api.external.SecurityExternalAPI;
import com.jbbcch.smarttaskmanager.user.api.UserInternalAPI;
import com.jbbcch.smarttaskmanager.user.dto.UserRequest;
import com.jbbcch.smarttaskmanager.user.dto.UserResponse;
import com.jbbcch.smarttaskmanager.user.mapper.UserMapper;
import com.jbbcch.smarttaskmanager.user.model.entity.User;
import com.jbbcch.smarttaskmanager.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserInternalAPI {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final SecurityExternalAPI securityExternalAPI;
    private final DepartmentAssignmentExternalAPI departmentAssignmentExternalAPI;

    @Override
    @Transactional
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.userRequestToUser(request);
        user.setCreatedBy(request.getActionBy());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        departmentAssignmentExternalAPI.assignDefaultDepartmentToUser(user.getId(), request.getActionBy());
        return userMapper.userToUserResponse(user);
    }

    @Override
    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.userToUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateUserById(UUID id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUserFromRequest(request, user);
        user.setUpdatedBy(request.getActionBy());
        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        userRepository.save(user);
        return userMapper.userToUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse deleteUserById(UUID id) {
        User deletedUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        deletedUser.setIsDeleted(true);
        userRepository.save(deletedUser);
        return userMapper.userToUserResponse(deletedUser);
    }

    @Override
    public UserResponse getCurrentUser() {
        UUID currentUserId = securityExternalAPI.getCurrentUserId();
        if (currentUserId != null) {
            return getUserById(currentUserId);
        }
        return null;
    }
}
