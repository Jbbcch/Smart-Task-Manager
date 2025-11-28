package com.jbbcch.smarttaskmanager.department.mapper;

import com.jbbcch.smarttaskmanager.department.dto.DepartmentUserRequest;
import com.jbbcch.smarttaskmanager.department.dto.DepartmentUserResponse;
import com.jbbcch.smarttaskmanager.department.model.entity.DepartmentUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentUserMapper {
    DepartmentUser departmentUserFromRequest(DepartmentUserRequest request);
    DepartmentUserResponse departmentUserToResponse(DepartmentUser departmentUser);
}
