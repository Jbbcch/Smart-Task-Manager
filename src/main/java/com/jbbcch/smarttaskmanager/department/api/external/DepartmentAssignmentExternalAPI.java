package com.jbbcch.smarttaskmanager.department.api.external;

import com.jbbcch.smarttaskmanager.department.dto.external.AssignedDepartmentResponse;

import java.util.List;
import java.util.UUID;

public interface DepartmentAssignmentExternalAPI {
    List<AssignedDepartmentResponse> getDepartmentsByUserId(UUID userId);
    void assignDefaultDepartmentToUser(UUID userId, UUID actionBy);
}
