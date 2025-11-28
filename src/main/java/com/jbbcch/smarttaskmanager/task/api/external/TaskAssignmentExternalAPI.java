package com.jbbcch.smarttaskmanager.task.api.external;

import com.jbbcch.smarttaskmanager.task.dto.external.AssignedTaskResponse;

import java.util.List;
import java.util.UUID;

public interface TaskAssignmentExternalAPI {
    List<AssignedTaskResponse> getTasksByUserId(UUID userId);
}
