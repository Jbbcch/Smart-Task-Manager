package com.jbbcch.smarttaskmanager.department.api;

import com.jbbcch.smarttaskmanager.department.dto.DepartmentRequest;
import com.jbbcch.smarttaskmanager.department.dto.DepartmentResponse;

public interface DepartmentInternalAPI {
    DepartmentResponse createDepartment(DepartmentRequest request);
    DepartmentResponse getDepartmentById(Long id);
    DepartmentResponse updateDepartmentById(Long id, DepartmentRequest request);
    DepartmentResponse deleteDepartmentById(Long id);
}
