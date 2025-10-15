package com.jbbcch.smarttaskmanager.task.api;

import com.jbbcch.smarttaskmanager.task.dto.TaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.TaskResponse;

import java.util.List;

public interface TaskInternalAPI {
        TaskResponse deleteTaskById(Long id);
        List<TaskResponse> getTasksByProjectId(Long projectId);
        TaskResponse createTask(TaskRequest request);
        TaskResponse updateTaskById(Long id, TaskRequest request);
}
