package com.jbbcch.smarttaskmanager.task.api.external;

import com.jbbcch.smarttaskmanager.task.dto.external.TaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskResponse;

import java.util.List;

public interface TaskExternalAPI {
    List<TaskResponse> getTasksByProjectId(Long projectId);
    TaskResponse createTask(Long projectId, TaskRequest request);
}
