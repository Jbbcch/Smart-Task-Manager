package com.jbbcch.smarttaskmanager.project.service;

import com.jbbcch.smarttaskmanager.project.api.ProjectAssignmentAPI;
import com.jbbcch.smarttaskmanager.project.api.external.ProjectAssignmentExternalAPI;
import com.jbbcch.smarttaskmanager.department.dto.external.AssignedDepartmentResponse;
import com.jbbcch.smarttaskmanager.project.dto.ProjectDepartmentRequest;
import com.jbbcch.smarttaskmanager.project.dto.ProjectDepartmentResponse;
import com.jbbcch.smarttaskmanager.project.dto.external.AssignedProjectResponse;
import com.jbbcch.smarttaskmanager.project.mapper.AssignedProjectMapper;
import com.jbbcch.smarttaskmanager.project.model.entity.AssignedProject;
import com.jbbcch.smarttaskmanager.project.repository.AssignedProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectAssignmentService implements ProjectAssignmentAPI, ProjectAssignmentExternalAPI {

    private final AssignedProjectRepository assignedProjectRepository;
    private final AssignedProjectMapper assignedProjectMapper;

    @Override
    @Transactional
    public ProjectDepartmentResponse assignProjectToDepartment(ProjectDepartmentRequest request) {
        AssignedProject assignedProject = assignedProjectMapper.requestToAssignedProject(request);
        assignedProject.setAssignedBy(request.getActionBy());

        try {
            assignedProjectRepository.save(assignedProject);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof ConstraintViolationException cve &&
                    "23503".equals(cve.getSQLState())) {  // postgres foreign key violation
                throw new RuntimeException("Invalid project or department id");
            }
            throw ex;
        }

        return assignedProjectMapper.assignedProjectToResponse(assignedProject);
    }

    @Override
    @Transactional
    public ProjectDepartmentResponse removeProjectFromDepartmentById(Long id) {
        AssignedProject removedProject = assignedProjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project-Department relation not found"));
        assignedProjectRepository.deleteById(id);
        return assignedProjectMapper.assignedProjectToResponse(removedProject);
    }

    @Override
    public List<AssignedProjectResponse> getProjectsByDepartmentId(Long departmentId) {
        return assignedProjectRepository.getProjectsByDepartmentId(departmentId);
    }

    @Override
    public List<AssignedDepartmentResponse> getDepartmentsByProjectId(Long projectId) {
        return assignedProjectRepository.getDepartmentsByProjectId(projectId);
    }
}
