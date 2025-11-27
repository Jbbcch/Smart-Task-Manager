package com.jbbcch.smarttaskmanager.project.api.external;

import com.jbbcch.smarttaskmanager.project.dto.external.AssignedProjectResponse;

import java.util.List;

public interface ProjectAssignmentExternalAPI {
    List<AssignedProjectResponse> getProjectsByDepartmentId(Long departmentId);
}
