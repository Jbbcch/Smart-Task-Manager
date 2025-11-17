package com.jbbcch.smarttaskmanager.user.controller;

import com.jbbcch.smarttaskmanager.user.api.UserInternalAPI;
import com.jbbcch.smarttaskmanager.user.dto.UserRequest;
import com.jbbcch.smarttaskmanager.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserInternalAPI userInternalAPI;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request) {
        UserResponse createdUser = userInternalAPI.createUser(request);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        UserResponse user = userInternalAPI.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserById(
            @PathVariable UUID id,
            @RequestBody @Valid UserRequest request
    ) {
        UserResponse updatedUser = userInternalAPI.updateUserById(id, request);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> softDeleteUserById(@PathVariable UUID id) {
        UserResponse deletedUser = userInternalAPI.deleteUserById(id);
        return ResponseEntity.ok(deletedUser);
    }
}
