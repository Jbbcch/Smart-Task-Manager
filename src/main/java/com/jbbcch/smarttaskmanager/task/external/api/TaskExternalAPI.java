package com.jbbcch.smarttaskmanager.task.external.api;

import com.jbbcch.smarttaskmanager.task.external.dto.TaskRequest;
import com.jbbcch.smarttaskmanager.task.external.dto.TaskResponse;

import java.util.List;

public interface TaskExternalAPI {
        TaskResponse createTask(TaskRequest request);
        TaskResponse updateTaskById(Long id, TaskRequest request);
        TaskResponse deleteTaskById(Long id);
        List<TaskResponse> getTasksByProjectId(Long id);
}
