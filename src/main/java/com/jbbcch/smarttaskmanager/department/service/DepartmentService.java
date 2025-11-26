package com.jbbcch.smarttaskmanager.department.service;

import com.jbbcch.smarttaskmanager.department.api.DepartmentInternalAPI;
import com.jbbcch.smarttaskmanager.department.dto.DepartmentRequest;
import com.jbbcch.smarttaskmanager.department.dto.DepartmentResponse;
import com.jbbcch.smarttaskmanager.department.mapper.DepartmentMapper;
import com.jbbcch.smarttaskmanager.department.model.entity.Department;
import com.jbbcch.smarttaskmanager.department.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService implements DepartmentInternalAPI {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    @Transactional
    public DepartmentResponse createDepartment(DepartmentRequest request) {
        Department department = departmentMapper.departmentRequestToDepartment(request);
        department.setCreatedBy(request.getActionBy());
        departmentRepository.save(department);
        return departmentMapper.departmentToDepartmentResponse(department);
    }

    @Override
    @Transactional
    public DepartmentResponse updateDepartmentById(Long id, DepartmentRequest request) {
        departmentRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Department not found"));
        Department department = departmentMapper.departmentRequestToDepartment(request);
        department.setId(id);
        department.setUpdatedBy(request.getActionBy());
        departmentRepository.save(department);
        return departmentMapper.departmentToDepartmentResponse(department);
    }

    @Override
    @Transactional
    public DepartmentResponse deleteDepartmentById(Long id) {
        Department deletedDepartment = departmentRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Department not found"));
        departmentRepository.deleteById(id);
        return departmentMapper.departmentToDepartmentResponse(deletedDepartment);
    }

    @Override
    @Transactional
    public DepartmentResponse getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Department not found"));
        return departmentMapper.departmentToDepartmentResponse(department);
    }
}
