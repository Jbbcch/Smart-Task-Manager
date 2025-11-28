package com.jbbcch.smarttaskmanager.department.api;

import com.jbbcch.smarttaskmanager.department.dto.DepartmentUserRequest;
import com.jbbcch.smarttaskmanager.department.dto.DepartmentUserResponse;
import com.jbbcch.smarttaskmanager.user.dto.external.AssignedUserResponse;

import java.util.List;

public interface DepartmentAssignmentAPI {
    DepartmentUserResponse assignUserToDepartment(DepartmentUserRequest request);
    DepartmentUserResponse removeUserFromDepartmentById(Long id);
    List<AssignedUserResponse> getUsersByDepartmentId(Long departmentId);
}
