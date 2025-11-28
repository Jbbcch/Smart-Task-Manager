package com.jbbcch.smarttaskmanager.task.controller;

import com.jbbcch.smarttaskmanager.security.role.dto.external.AssignedUserResponse;
import com.jbbcch.smarttaskmanager.task.api.TaskAssignmentAPI;
import com.jbbcch.smarttaskmanager.task.api.TaskInternalAPI;
import com.jbbcch.smarttaskmanager.task.api.external.SubtaskExternalAPI;
import com.jbbcch.smarttaskmanager.task.dto.TaskUserRequest;
import com.jbbcch.smarttaskmanager.task.dto.TaskUserResponse;
import com.jbbcch.smarttaskmanager.task.dto.SubtaskResponse;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskResponse;
import com.jbbcch.smarttaskmanager.task.model.enums.TaskStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskInternalAPI taskInternalAPI;
    private final SubtaskExternalAPI subtaskExternalAPI;
    private final TaskAssignmentAPI taskAssignmentAPI;


    @PreAuthorize("hasAuthority('CREATE_TASK')")
    @PostMapping
    ResponseEntity<TaskResponse> createTask(
            @RequestParam Long projectId,
            @RequestBody @Valid TaskRequest request
    ) {
        TaskResponse createdTask = taskInternalAPI.createTask(projectId, request);
        return ResponseEntity.ok(createdTask);
    }

    @PreAuthorize("hasAuthority('READ_TASK')")
    @GetMapping("/{id}")
    ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        TaskResponse response = taskInternalAPI.getTaskById(id);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('UPDATE_TASK')")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @RequestBody @Valid TaskRequest request
    ) {
        TaskResponse updatedTask = taskInternalAPI.updateTaskById(id, request);
        return ResponseEntity.ok(updatedTask);
    }

    @PreAuthorize("hasAuthority('READ_TASK')")
    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> changeTaskStatus(
            @PathVariable Long id,
            @RequestBody TaskStatus status
    ) {
        TaskResponse response = taskInternalAPI.changeTaskStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('DELETE_TASK')")
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable Long id) {
        TaskResponse deletedTask = taskInternalAPI.deleteTaskById(id);
        return ResponseEntity.ok(deletedTask);
    }

    @PreAuthorize("hasAuthority('READ_TASK')")
    @GetMapping("/{id}/subtasks")
    public ResponseEntity<List<SubtaskResponse>> getSubtasksByTaskId(@PathVariable Long id) {
        List<SubtaskResponse> subtaskResponseList = subtaskExternalAPI.getSubtasksByTaskId(id);
        return  ResponseEntity.ok(subtaskResponseList);
    }

    @PreAuthorize("hasAuthority('ASSIGN_TASK')")
    @PostMapping("/assignment")
    ResponseEntity<TaskUserResponse> assignTaskToUser(@RequestBody TaskUserRequest request) {
        TaskUserResponse assignedTask = taskAssignmentAPI.assignTaskToUser(request);
        return ResponseEntity.ok(assignedTask);
    }

    @PreAuthorize("hasAuthority('ASSIGN_TASK')")
    @DeleteMapping("/assignment/{id}")
    ResponseEntity<TaskUserResponse> removeTaskFromUserById(@PathVariable Long id) {
        TaskUserResponse removedTask = taskAssignmentAPI.removeTaskFromUserById(id);
        return ResponseEntity.ok(removedTask);
    }

    @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping("/{id}/users")
    ResponseEntity<List<AssignedUserResponse>> getAssignedUsersByTaskId(@PathVariable Long taskId) {
        List<AssignedUserResponse> assignedUsers = taskAssignmentAPI.getUsersByTaskId(taskId);
        return ResponseEntity.ok(assignedUsers);
    }
}
