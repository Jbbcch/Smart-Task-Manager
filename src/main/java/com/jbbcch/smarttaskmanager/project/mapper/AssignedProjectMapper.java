package com.jbbcch.smarttaskmanager.project.mapper;

import com.jbbcch.smarttaskmanager.project.dto.ProjectDepartmentRequest;
import com.jbbcch.smarttaskmanager.project.dto.ProjectDepartmentResponse;
import com.jbbcch.smarttaskmanager.project.model.entity.AssignedProject;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssignedProjectMapper {
    ProjectDepartmentResponse assignedProjectToResponse(AssignedProject assignedProject);
    AssignedProject requestToAssignedProject(ProjectDepartmentRequest request);
}
