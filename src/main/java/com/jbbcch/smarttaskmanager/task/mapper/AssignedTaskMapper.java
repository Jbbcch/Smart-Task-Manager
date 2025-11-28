package com.jbbcch.smarttaskmanager.task.mapper;

import com.jbbcch.smarttaskmanager.task.dto.TaskUserRequest;
import com.jbbcch.smarttaskmanager.task.dto.TaskUserResponse;
import com.jbbcch.smarttaskmanager.task.model.entity.AssignedTask;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssignedTaskMapper {
    TaskUserResponse assignedTaskToResponse(AssignedTask assignedTask);
    AssignedTask requestToAssignedTask(TaskUserRequest request);
}
