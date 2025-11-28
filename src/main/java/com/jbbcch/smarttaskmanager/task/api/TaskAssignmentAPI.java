package com.jbbcch.smarttaskmanager.task.api;

import com.jbbcch.smarttaskmanager.user.dto.external.AssignedUserResponse;
import com.jbbcch.smarttaskmanager.task.dto.TaskUserRequest;
import com.jbbcch.smarttaskmanager.task.dto.TaskUserResponse;

import java.util.List;

public interface TaskAssignmentAPI {
    TaskUserResponse assignTaskToUser(TaskUserRequest request);
    TaskUserResponse removeTaskFromUserById(Long id);
    List<AssignedUserResponse> getUsersByTaskId(Long taskId);
}
