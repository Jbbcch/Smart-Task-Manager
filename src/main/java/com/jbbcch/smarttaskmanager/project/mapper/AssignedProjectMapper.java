package com.jbbcch.smarttaskmanager.project.mapper;

import com.jbbcch.smarttaskmanager.project.dto.AssignedProjectRequest;
import com.jbbcch.smarttaskmanager.project.dto.AssignedProjectResponse;
import com.jbbcch.smarttaskmanager.project.model.entity.AssignedProject;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssignedProjectMapper {
    AssignedProjectResponse assignedProjectToResponse(AssignedProject assignedProject);
    AssignedProject requestToAssignedProject(AssignedProjectRequest request);
}
