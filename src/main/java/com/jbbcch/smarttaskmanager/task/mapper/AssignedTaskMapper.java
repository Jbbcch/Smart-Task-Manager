package com.jbbcch.smarttaskmanager.task.mapper;

import com.jbbcch.smarttaskmanager.task.dto.AssignedTaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.AssignedTaskResponse;
import com.jbbcch.smarttaskmanager.task.model.entity.AssignedTask;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssignedTaskMapper {
    AssignedTaskResponse assignedTaskToResponse(AssignedTask assignedTask);
    AssignedTask requestToAssignedTask(AssignedTaskRequest request);
}
