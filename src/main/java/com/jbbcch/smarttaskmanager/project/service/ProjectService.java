package com.jbbcch.smarttaskmanager.project.service;

import com.jbbcch.smarttaskmanager.project.api.ProjectInternalAPI;
import com.jbbcch.smarttaskmanager.project.dto.ProjectRequest;
import com.jbbcch.smarttaskmanager.project.dto.ProjectResponse;
import com.jbbcch.smarttaskmanager.project.mapper.ProjectMapper;
import com.jbbcch.smarttaskmanager.project.model.entity.Project;
import com.jbbcch.smarttaskmanager.project.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService implements ProjectInternalAPI {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    @Transactional
    public ProjectResponse createProject(ProjectRequest request) {
        Project project = projectMapper.projectRequestToProject(request);
        project.setCreatedBy(request.getActionBy());
        projectRepository.save(project);
        return projectMapper.projectToProjectResponse(project);
    }

    @Override
    @Transactional
    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return projectMapper.projectToProjectResponse(project);
    }

    @Override
    @Transactional
    public ProjectResponse updateProjectById(Long id, ProjectRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        projectMapper.updateProjectFromRequest(request, project);
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
}
