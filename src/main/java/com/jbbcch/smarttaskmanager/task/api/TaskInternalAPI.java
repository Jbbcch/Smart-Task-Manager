package com.jbbcch.smarttaskmanager.task.api;

import com.jbbcch.smarttaskmanager.task.dto.external.TaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskResponse;

public interface TaskInternalAPI {
        TaskResponse deleteTaskById(Long id);
        TaskResponse updateTaskById(Long id, TaskRequest request);
}
