package com.jbbcch.smarttaskmanager.task.controller;

import com.jbbcch.smarttaskmanager.task.api.TaskAssignmentAPI;
import com.jbbcch.smarttaskmanager.task.api.TaskInternalAPI;
import com.jbbcch.smarttaskmanager.task.api.external.SubtaskExternalAPI;
import com.jbbcch.smarttaskmanager.task.dto.AssignedTaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.AssignedTaskResponse;
import com.jbbcch.smarttaskmanager.task.dto.SubtaskResponse;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskInternalAPI taskInternalAPI;
    private final SubtaskExternalAPI subtaskExternalAPI;
    private final TaskAssignmentAPI taskAssignmentAPI;

    @PostMapping
    ResponseEntity<TaskResponse> createTask(
            @RequestParam Long projectId,
            @RequestBody @Valid TaskRequest request
    ) {
        TaskResponse createdTask = taskInternalAPI.createTask(projectId, request);
        return  ResponseEntity.ok(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @RequestBody @Valid TaskRequest request
    ) {
        TaskResponse updatedTask = taskInternalAPI.updateTaskById(id, request);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable Long id) {
        TaskResponse deletedTask = taskInternalAPI.deleteTaskById(id);
        return ResponseEntity.ok(deletedTask);
    }

    @GetMapping("/{id}/subtasks")
    public ResponseEntity<List<SubtaskResponse>> getSubtasksByTaskId(@PathVariable Long id) {
        List<SubtaskResponse> subtaskResponseList = subtaskExternalAPI.getSubtasksByTaskId(id);
        return  ResponseEntity.ok(subtaskResponseList);
    }

    @PostMapping("/assignment")
    ResponseEntity<AssignedTaskResponse> assignProjectToDepartment(@RequestBody AssignedTaskRequest request) {
        AssignedTaskResponse assignedTask = taskAssignmentAPI.assignTaskToUser(request);
        return ResponseEntity.ok(assignedTask);
    }

    @DeleteMapping("/assignment/{id}")
    ResponseEntity<AssignedTaskResponse> removeProjectFromDepartmentById(@PathVariable Long id) {
        AssignedTaskResponse removedTask = taskAssignmentAPI.removeTaskFromUserById(id);
        return ResponseEntity.ok(removedTask);
    }
}
