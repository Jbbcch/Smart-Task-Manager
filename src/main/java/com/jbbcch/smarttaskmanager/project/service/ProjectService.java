package com.jbbcch.smarttaskmanager.project.service;

import com.jbbcch.smarttaskmanager.project.api.ProjectAssignmentAPI;
import com.jbbcch.smarttaskmanager.project.api.ProjectInternalAPI;
import com.jbbcch.smarttaskmanager.project.dto.AssignedProjectRequest;
import com.jbbcch.smarttaskmanager.project.dto.AssignedProjectResponse;
import com.jbbcch.smarttaskmanager.project.dto.ProjectRequest;
import com.jbbcch.smarttaskmanager.project.dto.ProjectResponse;
import com.jbbcch.smarttaskmanager.project.mapper.AssignedProjectMapper;
import com.jbbcch.smarttaskmanager.project.mapper.ProjectMapper;
import com.jbbcch.smarttaskmanager.project.model.entity.AssignedProject;
import com.jbbcch.smarttaskmanager.project.model.entity.Project;
import com.jbbcch.smarttaskmanager.project.repository.AssignedProjectRepository;
import com.jbbcch.smarttaskmanager.project.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService implements ProjectInternalAPI, ProjectAssignmentAPI {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final AssignedProjectRepository assignedProjectRepository;
    private final AssignedProjectMapper assignedProjectMapper;

    @Override
    @Transactional
    public ProjectResponse createProject(ProjectRequest request) {
        Project project = projectMapper.projectRequestToProject(request);
        project.setCreatedBy(request.getActionBy());
        projectRepository.save(project);
        return projectMapper.projectToProjectResponse(project);
    }

    @Override
    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return projectMapper.projectToProjectResponse(project);
    }

    @Override
    @Transactional
    public ProjectResponse updateProjectById(Long id, ProjectRequest request) {
        projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        Project project = projectMapper.projectRequestToProject(request);
        project.setId(id);
        project.setUpdatedBy(request.getActionBy());
        projectRepository.save(project);
        return projectMapper.projectToProjectResponse(project);
    }

    @Override
    @Transactional
    public ProjectResponse deleteProjectById(Long id) {
        Project deletedProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        projectRepository.deleteById(id);
        return projectMapper.projectToProjectResponse(deletedProject);
    }

    @Override
    @Transactional
    public AssignedProjectResponse assignProjectToDepartment(AssignedProjectRequest request) {
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
    public AssignedProjectResponse removeProjectFromDepartmentById(Long id) {
        AssignedProject removedProject = assignedProjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project-Department relation not found"));
        assignedProjectRepository.deleteById(id);
        return assignedProjectMapper.assignedProjectToResponse(removedProject);
    }
}
