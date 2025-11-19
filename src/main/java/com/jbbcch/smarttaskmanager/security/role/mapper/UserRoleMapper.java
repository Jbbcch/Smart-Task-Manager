package com.jbbcch.smarttaskmanager.security.role.mapper;

import com.jbbcch.smarttaskmanager.security.role.dto.UserRoleRequest;
import com.jbbcch.smarttaskmanager.security.role.model.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleMapper {
    UserRole userRoleRequestToUserRole(UserRoleRequest request);
}
