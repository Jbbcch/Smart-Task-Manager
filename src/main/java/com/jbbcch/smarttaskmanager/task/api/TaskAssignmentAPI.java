package com.jbbcch.smarttaskmanager.task.api;

import com.jbbcch.smarttaskmanager.task.dto.AssignedTaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.AssignedTaskResponse;

public interface TaskAssignmentAPI {
    AssignedTaskResponse assignTaskToUser(AssignedTaskRequest request);
    AssignedTaskResponse removeTaskFromUserById(Long id);
}
