package com.jbbcch.smarttaskmanager.project.api;

import com.jbbcch.smarttaskmanager.project.dto.AssignedProjectRequest;
import com.jbbcch.smarttaskmanager.project.dto.AssignedProjectResponse;

public interface ProjectAssignmentAPI {
    AssignedProjectResponse assignProjectToDepartment(AssignedProjectRequest request);
    AssignedProjectResponse removeProjectFromDepartmentById(Long departmentId);
}
