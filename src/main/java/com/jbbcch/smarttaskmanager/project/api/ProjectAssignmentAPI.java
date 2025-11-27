package com.jbbcch.smarttaskmanager.project.api;

import com.jbbcch.smarttaskmanager.project.dto.ProjectDepartmentRequest;
import com.jbbcch.smarttaskmanager.project.dto.ProjectDepartmentResponse;

public interface ProjectAssignmentAPI {
    ProjectDepartmentResponse assignProjectToDepartment(ProjectDepartmentRequest request);
    ProjectDepartmentResponse removeProjectFromDepartmentById(Long departmentId);
}
