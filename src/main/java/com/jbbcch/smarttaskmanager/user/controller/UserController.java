package com.jbbcch.smarttaskmanager.user.controller;

import com.jbbcch.smarttaskmanager.security.role.api.external.RoleAssignmentExternalAPI;
import com.jbbcch.smarttaskmanager.security.role.dto.external.AssignedRolesResponse;
import com.jbbcch.smarttaskmanager.task.api.external.TaskAssignmentExternalAPI;
import com.jbbcch.smarttaskmanager.task.dto.external.AssignedTaskResponse;
import com.jbbcch.smarttaskmanager.user.api.UserInternalAPI;
import com.jbbcch.smarttaskmanager.user.dto.UserRequest;
import com.jbbcch.smarttaskmanager.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserInternalAPI userInternalAPI;
    private final RoleAssignmentExternalAPI roleAssignmentExternalAPI;
    private final TaskAssignmentExternalAPI taskAssignmentExternalAPI;

    @PreAuthorize("hasAuthority('CREATE_USER')")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request) {
        UserResponse createdUser = userInternalAPI.createUser(request);
        return ResponseEntity.ok(createdUser);
    }

    @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        UserResponse user = userInternalAPI.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser() {
        UserResponse user = userInternalAPI.getCurrentUser();
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAuthority('UPDATE_USER') or #id == authentication.principle.id")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserById(
            @PathVariable UUID id,
            @RequestBody @Valid UserRequest request
    ) {
        UserResponse updatedUser = userInternalAPI.updateUserById(id, request);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasAuthority('DELETE_USER') or #id == authentication.principle.id")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> softDeleteUserById(@PathVariable UUID id) {
        UserResponse deletedUser = userInternalAPI.deleteUserById(id);
        return ResponseEntity.ok(deletedUser);
    }

    @PreAuthorize("hasAuthority('READ_ROLE')")
    @GetMapping("/{id}/roles")
    public ResponseEntity<List<AssignedRolesResponse>> getUserRoles(@PathVariable UUID userId) {
        List<AssignedRolesResponse> userRoles = roleAssignmentExternalAPI.getUserRolesByUserId(userId);
        return ResponseEntity.ok(userRoles);
    }

    @PreAuthorize("hasAuthority('READ_TASK')")
    @GetMapping("/{id}/tasks")
    ResponseEntity<List<AssignedTaskResponse>> getAssignedTasksByUserId(@PathVariable UUID userId) {
        List<AssignedTaskResponse> assignedTasks = taskAssignmentExternalAPI.getTasksByUserId(userId);
        return ResponseEntity.ok(assignedTasks);
    }
}
