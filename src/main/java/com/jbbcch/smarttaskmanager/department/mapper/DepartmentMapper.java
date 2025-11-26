package com.jbbcch.smarttaskmanager.department.mapper;

import com.jbbcch.smarttaskmanager.department.dto.DepartmentRequest;
import com.jbbcch.smarttaskmanager.department.dto.DepartmentResponse;
import com.jbbcch.smarttaskmanager.department.model.entity.Department;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {
    DepartmentResponse departmentToDepartmentResponse(Department department);
    Department departmentRequestToDepartment(DepartmentRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDepartmentFromRequest(DepartmentRequest request, @MappingTarget Department department);
}
