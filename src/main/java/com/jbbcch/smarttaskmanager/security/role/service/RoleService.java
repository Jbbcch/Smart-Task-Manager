package com.jbbcch.smarttaskmanager.security.role.service;

import com.jbbcch.smarttaskmanager.security.role.api.RoleInternalAPI;
import com.jbbcch.smarttaskmanager.security.role.dto.RoleRequest;
import com.jbbcch.smarttaskmanager.security.role.dto.RoleResponse;
import com.jbbcch.smarttaskmanager.security.role.mapper.RoleMapper;
import com.jbbcch.smarttaskmanager.security.role.model.entity.Role;
import com.jbbcch.smarttaskmanager.security.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleInternalAPI {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleResponse createRole(RoleRequest roleRequest) {
        Role createdRole = roleMapper.roleRequestToRole(roleRequest);
        createdRole.setCreatedBy(roleRequest.getActionBy());
        roleRepository.save(createdRole);
        return roleMapper.roleToRoleResponse(createdRole);
    }

    @Override
    public RoleResponse updateRoleById(Long id, RoleRequest roleRequest) {
        roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        Role updatedRole = roleMapper.roleRequestToRole(roleRequest);
        updatedRole.setId(id);
        updatedRole.setUpdatedBy(roleRequest.getActionBy());
        roleRepository.save(updatedRole);
        return roleMapper.roleToRoleResponse(updatedRole);
    }

    @Override
    public RoleResponse getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return roleMapper.roleToRoleResponse(role);
    }

    @Override
    public RoleResponse deleteRoleById(Long id) {
        Role deletedRole = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        roleRepository.deleteById(id);
        return roleMapper.roleToRoleResponse(deletedRole);
    }
}
