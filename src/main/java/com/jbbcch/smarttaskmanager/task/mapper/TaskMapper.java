package com.jbbcch.smarttaskmanager.task.mapper;

import com.jbbcch.smarttaskmanager.task.dto.TaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskResponse;
import com.jbbcch.smarttaskmanager.task.model.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {
    TaskResponse taskToTaskResponse(Task task);
    Task taskRequestToTask(TaskRequest taskRequest);
}
