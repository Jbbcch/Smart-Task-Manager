package com.jbbcch.smarttaskmanager.security.role.mapper;

import com.jbbcch.smarttaskmanager.security.role.dto.RoleRequest;
import com.jbbcch.smarttaskmanager.security.role.dto.RoleResponse;
import com.jbbcch.smarttaskmanager.security.role.model.entity.Role;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {
    RoleResponse roleToRoleResponse(Role role);
    Role roleRequestToRole(RoleRequest roleRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRoleFromRequest(RoleRequest request, @MappingTarget Role role);
}
