package com.jbbcch.smarttaskmanager.task.api;

import com.jbbcch.smarttaskmanager.task.dto.TaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskResponse;

public interface TaskInternalAPI {
        TaskResponse deleteTaskById(Long id);
        TaskResponse createTask(TaskRequest request);
        TaskResponse updateTaskById(Long id, TaskRequest request);
}
