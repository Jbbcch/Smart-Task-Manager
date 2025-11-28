package com.jbbcch.smarttaskmanager.project.api;

import com.jbbcch.smarttaskmanager.project.dto.AssignedDepartmentResponse;
import com.jbbcch.smarttaskmanager.project.dto.ProjectDepartmentRequest;
import com.jbbcch.smarttaskmanager.project.dto.ProjectDepartmentResponse;

import java.util.List;

public interface ProjectAssignmentAPI {
    ProjectDepartmentResponse assignProjectToDepartment(ProjectDepartmentRequest request);
    ProjectDepartmentResponse removeProjectFromDepartmentById(Long departmentId);
    List<AssignedDepartmentResponse> getDepartmentsByProjectId(Long departmentId);
}
