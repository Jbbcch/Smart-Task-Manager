package com.jbbcch.smarttaskmanager.security.role.api;

import com.jbbcch.smarttaskmanager.security.role.dto.UserRoleRequest;
import com.jbbcch.smarttaskmanager.security.role.dto.external.UserRoleResponse;

public interface RoleAssignmentAPI {
    UserRoleResponse assignRoleToUser(UserRoleRequest request);
    UserRoleResponse removeRoleFromUserById(Long id);
}
