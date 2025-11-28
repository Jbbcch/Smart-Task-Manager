package com.jbbcch.smarttaskmanager.security.role.api;

import com.jbbcch.smarttaskmanager.user.dto.external.AssignedUserResponse;
import com.jbbcch.smarttaskmanager.security.role.dto.UserRoleRequest;
import com.jbbcch.smarttaskmanager.security.role.dto.UserRoleResponse;

import java.util.List;

public interface RoleAssignmentAPI {
    UserRoleResponse assignRoleToUser(UserRoleRequest request);
    UserRoleResponse removeRoleFromUserById(Long id);
    List<AssignedUserResponse> getRoleUsersByRoleId(Long roleId);
}
