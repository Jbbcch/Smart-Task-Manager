package com.jbbcch.smarttaskmanager.security.role.service;

import com.jbbcch.smarttaskmanager.security.role.api.RoleAssignmentAPI;
import com.jbbcch.smarttaskmanager.security.role.api.RoleInternalAPI;
import com.jbbcch.smarttaskmanager.security.role.api.external.RoleAssignmentExternalAPI;
import com.jbbcch.smarttaskmanager.security.role.dto.RoleRequest;
import com.jbbcch.smarttaskmanager.security.role.dto.RoleResponse;
import com.jbbcch.smarttaskmanager.security.role.dto.UserRoleRequest;
import com.jbbcch.smarttaskmanager.security.role.dto.external.UserRoleResponse;
import com.jbbcch.smarttaskmanager.security.role.mapper.RoleMapper;
import com.jbbcch.smarttaskmanager.security.role.mapper.UserRoleMapper;
import com.jbbcch.smarttaskmanager.security.role.model.entity.Role;
import com.jbbcch.smarttaskmanager.security.role.model.entity.UserRole;
import com.jbbcch.smarttaskmanager.security.role.repository.RoleRepository;
import com.jbbcch.smarttaskmanager.security.role.repository.UserRoleRepository;
import com.jbbcch.smarttaskmanager.security.shared.Authority;
import com.jbbcch.smarttaskmanager.security.shared.AuthorityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleInternalAPI, RoleAssignmentAPI, RoleAssignmentExternalAPI {

    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public RoleResponse createRole(RoleRequest roleRequest) {
        Role createdRole = roleMapper.roleRequestToRole(roleRequest);
        createdRole.setCreatedBy(roleRequest.getActionBy());
        roleRepository.save(createdRole);

        List<Authority> authorities = roleRequest.getAuthorities().stream()
                .map(Authority::fromString).toList();
        authorities.forEach(authority -> authority.setRoleId(createdRole.getId()));
        authorityRepository.saveAll(authorities);

        RoleResponse roleResponse = roleMapper.roleToRoleResponse(createdRole);
        roleResponse.setAuthorities(roleRequest.getAuthorities());
        // don't like this ^^^, but also don't care enough to write anything better
        // was there a reason not to have List<Authority> in Role? I don't remember.

        return roleResponse;
    }

    @Override
    @Transactional
    public RoleResponse updateRoleById(Long id, RoleRequest roleRequest) {
        roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Role updatedRole = roleMapper.roleRequestToRole(roleRequest);
        updatedRole.setId(id);
        updatedRole.setUpdatedBy(roleRequest.getActionBy());
        roleRepository.save(updatedRole);

        List<Authority> authorities = roleRequest.getAuthorities().stream()
                .map(Authority::fromString).toList();
        authorities.forEach(authority -> authority.setRoleId(id));
        authorityRepository.saveAll(authorities);

        return roleMapper.roleToRoleResponse(updatedRole);
    }

    @Override
    public RoleResponse getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        RoleResponse roleResponse = roleMapper.roleToRoleResponse(role);
        List<String> authorities = authorityRepository.findAuthoritiesByRoleId(id).stream()
                .map(Authority::toString).toList();
        roleResponse.setAuthorities(authorities);

        return roleResponse;
    }

    @Override
    @Transactional
    public RoleResponse deleteRoleById(Long id) {
        Role deletedRole = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        roleRepository.deleteById(id);
        authorityRepository.deleteByRoleId(id);

        // not important to know what authorities it had once it's deleted
        // thus, not going to bother with setting them for the response
        return roleMapper.roleToRoleResponse(deletedRole);
    }

    @Override
    @Transactional
    public UserRoleResponse assignRoleToUser(UserRoleRequest request) {
        UserRole assignedUserRole = userRoleMapper.userRoleRequestToUserRole(request);
        userRoleRepository.save(assignedUserRole);
        assignedUserRole.setAssignedBy(request.getActionBy());
        return userRoleMapper.userRoleToUserRoleResponse(assignedUserRole);
    }

    @Override
    @Transactional
    public UserRoleResponse removeRoleFromUserById(Long id) {
        UserRole removedUserRole = userRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User-Role relation not found"));
        userRoleRepository.deleteById(id);
        return userRoleMapper.userRoleToUserRoleResponse(removedUserRole);
    }

    @Override
    public List<UserRoleResponse> getUserRolesByUserId(UUID userId) {
        List<UserRole> userRoles = userRoleRepository.findUserRolesByUserId(userId);
        return userRoles.stream()
                .map(userRoleMapper::userRoleToUserRoleResponse)
                .toList();
    }
}
