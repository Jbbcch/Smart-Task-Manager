package com.jbbcch.smarttaskmanager.task.api;

import com.jbbcch.smarttaskmanager.task.dto.external.TaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskResponse;
import com.jbbcch.smarttaskmanager.task.model.enums.TaskStatus;

public interface TaskInternalAPI {
        TaskResponse createTask(Long projectId, TaskRequest request);
        TaskResponse getTaskById(Long id);
        TaskResponse deleteTaskById(Long id);
        TaskResponse updateTaskById(Long id, TaskRequest request);
        TaskResponse changeTaskStatus(Long id, TaskStatus status);
}
