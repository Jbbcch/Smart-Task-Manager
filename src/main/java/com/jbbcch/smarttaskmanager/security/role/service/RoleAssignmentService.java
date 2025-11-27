package com.jbbcch.smarttaskmanager.security.role.service;

import com.jbbcch.smarttaskmanager.security.role.api.RoleAssignmentAPI;
import com.jbbcch.smarttaskmanager.security.role.api.external.RoleAssignmentExternalAPI;
import com.jbbcch.smarttaskmanager.security.role.dto.UserRoleRequest;
import com.jbbcch.smarttaskmanager.security.role.dto.UserRoleResponse;
import com.jbbcch.smarttaskmanager.security.role.dto.external.AssignedRolesResponse;
import com.jbbcch.smarttaskmanager.security.role.mapper.UserRoleMapper;
import com.jbbcch.smarttaskmanager.security.role.model.entity.UserRole;
import com.jbbcch.smarttaskmanager.security.role.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleAssignmentService implements RoleAssignmentAPI, RoleAssignmentExternalAPI {

    private final UserRoleMapper userRoleMapper;
    private final UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public UserRoleResponse assignRoleToUser(UserRoleRequest request) {
        UserRole assignedUserRole = userRoleMapper.userRoleRequestToUserRole(request);
        assignedUserRole.setAssignedBy(request.getActionBy());

        try {
            userRoleRepository.save(assignedUserRole);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof ConstraintViolationException cve &&
                    "23503".equals(cve.getSQLState())) {  // postgres foreign key violation
                throw new RuntimeException("Invalid role or user id");
            }
            throw ex;
        }

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
    public List<AssignedRolesResponse> getUserRolesByUserId(UUID userId) {
        return userRoleRepository.findAssignedRolesByUserId(userId);
    }
}
