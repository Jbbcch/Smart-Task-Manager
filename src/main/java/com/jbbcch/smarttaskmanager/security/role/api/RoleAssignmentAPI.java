package com.jbbcch.smarttaskmanager.security.role.api;

import com.jbbcch.smarttaskmanager.security.role.dto.UserRoleRequest;

public interface RoleAssignmentAPI {
    void assignRoleToUser(UserRoleRequest request);
    void removeRoleFromUser(UserRoleRequest request);
}
