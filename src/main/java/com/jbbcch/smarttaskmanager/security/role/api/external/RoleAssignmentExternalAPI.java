package com.jbbcch.smarttaskmanager.security.role.api.external;

import com.jbbcch.smarttaskmanager.security.role.dto.external.UserRoleResponse;

import java.util.List;
import java.util.UUID;

public interface RoleAssignmentExternalAPI {
    List<UserRoleResponse> getUserRolesByUserId(UUID userId);
}
