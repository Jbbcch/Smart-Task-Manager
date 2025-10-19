package com.jbbcch.smarttaskmanager.project.api;

import com.jbbcch.smarttaskmanager.project.dto.ProjectRequest;
import com.jbbcch.smarttaskmanager.project.dto.ProjectResponse;

public interface ProjectInternalAPI {
    ProjectResponse createProject(ProjectRequest request);
    ProjectResponse getProjectById(Long id);
    ProjectResponse updateProjectById(Long id, ProjectRequest request);
    ProjectResponse deleteProjectById(Long id);
}
