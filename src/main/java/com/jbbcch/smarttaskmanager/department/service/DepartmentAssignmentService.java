package com.jbbcch.smarttaskmanager.department.service;

import com.jbbcch.smarttaskmanager.department.api.DepartmentAssignmentAPI;
import com.jbbcch.smarttaskmanager.department.api.external.DepartmentAssignmentExternalAPI;
import com.jbbcch.smarttaskmanager.department.dto.DepartmentUserRequest;
import com.jbbcch.smarttaskmanager.department.dto.DepartmentUserResponse;
import com.jbbcch.smarttaskmanager.department.mapper.DepartmentUserMapper;
import com.jbbcch.smarttaskmanager.department.model.entity.DepartmentUser;
import com.jbbcch.smarttaskmanager.department.repository.DepartmentUserRepository;
import com.jbbcch.smarttaskmanager.department.dto.external.AssignedDepartmentResponse;
import com.jbbcch.smarttaskmanager.user.dto.external.AssignedUserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentAssignmentService implements DepartmentAssignmentAPI, DepartmentAssignmentExternalAPI {

    private final DepartmentUserRepository departmentUserRepository;
    private final DepartmentUserMapper departmentUserMapper;

    @Override
    @Transactional
    public DepartmentUserResponse assignUserToDepartment(DepartmentUserRequest request) {
        DepartmentUser departmentUser = departmentUserMapper.departmentUserFromRequest(request);
        departmentUser.setAssignedBy(request.getActionBy());

        try {
            departmentUserRepository.save(departmentUser);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof ConstraintViolationException cve &&
                    "23503".equals(cve.getSQLState())) {  // postgres foreign key violation
                throw new RuntimeException("Invalid user or department id");
            }
            throw ex;
        }

        return departmentUserMapper.departmentUserToResponse(departmentUser);
    }

    @Override
    @Transactional
    public DepartmentUserResponse removeUserFromDepartmentById(Long id) {
        DepartmentUser departmentUser = departmentUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department-User relation nt found"));
        departmentUserRepository.deleteById(id);
        return departmentUserMapper.departmentUserToResponse(departmentUser);
    }

    @Override
    public List<AssignedDepartmentResponse> getDepartmentsByUserId(UUID userId) {
        return departmentUserRepository.getDepartmentsByUserId(userId);
    }

    @Override
    public List<AssignedUserResponse> getUsersByDepartmentId(Long departmentId) {
        return departmentUserRepository.getUsersByDepartmentId(departmentId);
    }
}
