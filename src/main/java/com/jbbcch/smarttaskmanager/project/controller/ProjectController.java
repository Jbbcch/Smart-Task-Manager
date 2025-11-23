package com.jbbcch.smarttaskmanager.project.controller;

import com.jbbcch.smarttaskmanager.project.api.ProjectAssignmentAPI;
import com.jbbcch.smarttaskmanager.project.api.ProjectInternalAPI;
import com.jbbcch.smarttaskmanager.project.dto.AssignedProjectRequest;
import com.jbbcch.smarttaskmanager.project.dto.AssignedProjectResponse;
import com.jbbcch.smarttaskmanager.project.dto.ProjectRequest;
import com.jbbcch.smarttaskmanager.project.dto.ProjectResponse;
import com.jbbcch.smarttaskmanager.task.api.external.TaskExternalAPI;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectInternalAPI projectInternalAPI;
    private final TaskExternalAPI taskExternalAPI;
    private final ProjectAssignmentAPI projectAssignmentAPI;

    @GetMapping("/{id}")
    ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        ProjectResponse project = projectInternalAPI.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/{id}/tasks")
    ResponseEntity<List<TaskResponse>> getProjectTasks(@PathVariable Long id) {
        List<TaskResponse> projectTasks = taskExternalAPI.getTasksByProjectId(id);
        return ResponseEntity.ok(projectTasks);
    }

    @PostMapping("/{id}/tasks")
    ResponseEntity<TaskResponse> createTask(
            @PathVariable Long projectId,
            @RequestBody @Valid TaskRequest request
    ) {
        TaskResponse createdTask = taskExternalAPI.createTask(projectId, request);
        return  ResponseEntity.ok(createdTask);
    }

    @PostMapping
    ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest request) {
        ProjectResponse createdProject = projectInternalAPI.createProject(request);
        return ResponseEntity.ok(createdProject);
    }

    @PutMapping("/{id}")
    ResponseEntity<ProjectResponse> updateProjectById(
            @PathVariable Long id,
            @RequestBody @Valid ProjectRequest request
    ) {
        ProjectResponse updatedProject = projectInternalAPI.updateProjectById(id, request);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ProjectResponse> deleteProjectById(@PathVariable Long id) {
        ProjectResponse deletedProject = projectInternalAPI.deleteProjectById(id);
        return ResponseEntity.ok(deletedProject);
    }

    @PostMapping("/assignment")
    ResponseEntity<AssignedProjectResponse> assignProjectToDepartment(@RequestBody AssignedProjectRequest request) {
        AssignedProjectResponse assignedProject = projectAssignmentAPI.assignProjectToDepartment(request);
        return ResponseEntity.ok(assignedProject);
    }

    @DeleteMapping("/assignment/{id}")
    ResponseEntity<AssignedProjectResponse> removeProjectFromDepartmentById(@PathVariable Long id) {
        AssignedProjectResponse assignedProject = projectAssignmentAPI.removeProjectFromDepartmentById(id);
        return ResponseEntity.ok(assignedProject);
    }
}
