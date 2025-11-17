package com.jbbcch.smarttaskmanager.security.role.api;

import com.jbbcch.smarttaskmanager.security.role.dto.RoleRequest;
import com.jbbcch.smarttaskmanager.security.role.dto.RoleResponse;

public interface RoleInternalAPI {
    RoleResponse createRole(RoleRequest roleRequest);
    RoleResponse updateRoleById(Long id, RoleRequest roleRequest);
    RoleResponse getRoleById(Long id);
    RoleResponse deleteRoleById(Long id);
}
