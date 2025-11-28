package com.jbbcch.smarttaskmanager.security.role.service;

import com.jbbcch.smarttaskmanager.exceptions.ResourceNotFoundException;
import com.jbbcch.smarttaskmanager.security.role.api.RoleInternalAPI;
import com.jbbcch.smarttaskmanager.security.role.dto.RoleRequest;
import com.jbbcch.smarttaskmanager.security.role.dto.RoleResponse;
import com.jbbcch.smarttaskmanager.security.role.mapper.RoleMapper;
import com.jbbcch.smarttaskmanager.security.role.model.entity.Role;
import com.jbbcch.smarttaskmanager.security.role.repository.RoleRepository;
import com.jbbcch.smarttaskmanager.security.shared.Authority;
import com.jbbcch.smarttaskmanager.security.shared.AuthorityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleInternalAPI {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;
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
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        roleMapper.updateRoleFromRequest(roleRequest, role);
        role.setUpdatedBy(roleRequest.getActionBy());
        roleRepository.save(role);

        if (roleRequest.getAuthorities() != null) {
            // there probably is a better way of doing this
            authorityRepository.deleteByRoleId(id);
            List<Authority> authorities = roleRequest.getAuthorities().stream()
                    .map(Authority::fromString).toList();
            authorities.forEach(authority -> authority.setRoleId(id));
            authorityRepository.saveAll(authorities);
        }

        RoleResponse roleResponse = roleMapper.roleToRoleResponse(role);
        roleResponse.setAuthorities(roleRequest.getAuthorities());

        return roleResponse;
    }

    @Override
    @Transactional
    public RoleResponse getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

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
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        roleRepository.deleteById(id);

        // not important to know what authorities it had once it's deleted
        // thus, not going to bother with setting them for the response
        return roleMapper.roleToRoleResponse(deletedRole);
    }
}
