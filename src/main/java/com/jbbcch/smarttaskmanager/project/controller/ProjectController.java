package com.jbbcch.smarttaskmanager.project.controller;

import com.jbbcch.smarttaskmanager.project.api.ProjectInternalAPI;
import com.jbbcch.smarttaskmanager.project.dto.ProjectRequest;
import com.jbbcch.smarttaskmanager.project.dto.ProjectResponse;
import com.jbbcch.smarttaskmanager.task.api.external.TaskExternalAPI;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectInternalAPI projectInternalAPI;
    private final TaskExternalAPI taskExternalAPI;

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

    @PostMapping
    ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest request) {
        ProjectResponse createdProject = projectInternalAPI.createProject(request);
        return ResponseEntity.ok(createdProject);
    }

    @PutMapping("/{id}")
    ResponseEntity<ProjectResponse> updateProjectById(
            @PathVariable Long id,
            @RequestBody ProjectRequest request
    ) {
        ProjectResponse updatedProject = projectInternalAPI.updateProjectById(id, request);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ProjectResponse> deleteProjectById(@PathVariable Long id) {
        ProjectResponse deletedProject = projectInternalAPI.deleteProjectById(id);
        return ResponseEntity.ok(deletedProject);
    }
}
