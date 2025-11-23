package com.jbbcch.smarttaskmanager.security.role.controller;

import com.jbbcch.smarttaskmanager.security.role.api.RoleAssignmentAPI;
import com.jbbcch.smarttaskmanager.security.role.api.RoleInternalAPI;
import com.jbbcch.smarttaskmanager.security.role.dto.RoleRequest;
import com.jbbcch.smarttaskmanager.security.role.dto.RoleResponse;
import com.jbbcch.smarttaskmanager.security.role.dto.UserRoleRequest;
import com.jbbcch.smarttaskmanager.security.role.dto.UserRoleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleInternalAPI roleInternalAPI;
    private final RoleAssignmentAPI roleAssignmentAPI;

    @PostMapping
    public ResponseEntity<RoleResponse> createRole(@RequestBody RoleRequest roleRequest) {
        RoleResponse createdRole = roleInternalAPI.createRole(roleRequest);
        return ResponseEntity.ok(createdRole);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable Long id) {
        RoleResponse role = roleInternalAPI.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    @PutMapping("/{id}")
    ResponseEntity<RoleResponse> updateRoleById(
            @PathVariable Long id,
            @RequestBody @Valid RoleRequest request
    ) {
        RoleResponse updatedUser = roleInternalAPI.updateRoleById(id, request);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<RoleResponse> deleteRoleById(@PathVariable Long id) {
        RoleResponse deletedUser = roleInternalAPI.deleteRoleById(id);
        return ResponseEntity.ok(deletedUser);
    }

    @PostMapping("/assignment")
    public ResponseEntity<UserRoleResponse> assignRoleToUser(@RequestBody UserRoleRequest userRoleRequest) {
        UserRoleResponse response = roleAssignmentAPI.assignRoleToUser(userRoleRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/assignment/{id}")
    public ResponseEntity<UserRoleResponse> removeRoleFromUser(@PathVariable Long id) {
        UserRoleResponse response = roleAssignmentAPI.removeRoleFromUserById(id);
        return ResponseEntity.ok(response);
    }
}
