package com.jbbcch.smarttaskmanager.task.mapper;

import com.jbbcch.smarttaskmanager.task.external.dto.TaskRequest;
import com.jbbcch.smarttaskmanager.task.external.dto.TaskResponse;
import com.jbbcch.smarttaskmanager.task.model.entity.Task;
import org.mapstruct.Mapper;

@Mapper
public interface TaskMapper {
    TaskResponse taskToTaskResponse(Task task);
    Task taskRequestToTask(TaskRequest taskRequest);
}
