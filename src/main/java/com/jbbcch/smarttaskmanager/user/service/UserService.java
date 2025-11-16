package com.jbbcch.smarttaskmanager.user.service;

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

    @Override
    @Transactional
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.userRequestToUser(request);
        user.setCreatedBy(request.getActionBy());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
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
        userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User updatedUser = userMapper.userRequestToUser(request);
        updatedUser.setId(id);
        updatedUser.setPassword(passwordEncoder.encode(request.getPassword()));
        updatedUser.setUpdatedBy(request.getActionBy());
        userRepository.save(updatedUser);
        return userMapper.userToUserResponse(updatedUser);
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
}
