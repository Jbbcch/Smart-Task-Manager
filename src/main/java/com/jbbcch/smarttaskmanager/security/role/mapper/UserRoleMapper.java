package com.jbbcch.smarttaskmanager.security.role.mapper;

import com.jbbcch.smarttaskmanager.security.role.dto.UserRoleRequest;
import com.jbbcch.smarttaskmanager.security.role.dto.external.UserRoleResponse;
import com.jbbcch.smarttaskmanager.security.role.model.entity.Role;
import com.jbbcch.smarttaskmanager.security.role.model.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { RoleMapper.class })
public interface UserRoleMapper {
    UserRole userRoleRequestToUserRole(UserRoleRequest request);
    UserRoleResponse userRoleToUserRoleResponse(UserRole userRole);

    default Role map(Long roleId) {
        if (roleId == null) return null;
        Role role = new Role();
        role.setId(roleId);
        return role;
    }
}
