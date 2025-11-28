package com.jbbcch.smarttaskmanager.project.controller;

import com.jbbcch.smarttaskmanager.project.api.ProjectAssignmentAPI;
import com.jbbcch.smarttaskmanager.project.api.ProjectInternalAPI;
import com.jbbcch.smarttaskmanager.project.dto.*;
import com.jbbcch.smarttaskmanager.department.dto.external.AssignedDepartmentResponse;
import com.jbbcch.smarttaskmanager.task.api.external.TaskExternalAPI;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectInternalAPI projectInternalAPI;
    private final TaskExternalAPI taskExternalAPI;
    private final ProjectAssignmentAPI projectAssignmentAPI;

    @PreAuthorize("hasAuthority('READ_PROJECT')")
    @GetMapping("/{id}")
    ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        ProjectResponse project = projectInternalAPI.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @PreAuthorize("hasAuthority('READ_TASK')")
    @GetMapping("/{id}/tasks")
    ResponseEntity<List<TaskResponse>> getProjectTasks(@PathVariable Long id) {
        List<TaskResponse> projectTasks = taskExternalAPI.getTasksByProjectId(id);
        return ResponseEntity.ok(projectTasks);
    }

    @PreAuthorize("hasAuthority('CREATE_PROJECT')")
    @PostMapping
    ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest request) {
        ProjectResponse createdProject = projectInternalAPI.createProject(request);
        return ResponseEntity.ok(createdProject);
    }

    @PreAuthorize("hasAuthority('UPDATE_PROJECT')")
    @PutMapping("/{id}")
    ResponseEntity<ProjectResponse> updateProjectById(
            @PathVariable Long id,
            @RequestBody @Valid ProjectRequest request
    ) {
        ProjectResponse updatedProject = projectInternalAPI.updateProjectById(id, request);
        return ResponseEntity.ok(updatedProject);
    }

    @PreAuthorize("hasAuthority('DELETE_PROJECT')")
    @DeleteMapping("/{id}")
    ResponseEntity<ProjectResponse> deleteProjectById(@PathVariable Long id) {
        ProjectResponse deletedProject = projectInternalAPI.deleteProjectById(id);
        return ResponseEntity.ok(deletedProject);
    }

    @PreAuthorize("hasAuthority('ASSIGN_PROJECT_DEPARTMENT')")
    @PostMapping("/assignment")
    ResponseEntity<ProjectDepartmentResponse> assignProjectToDepartment(@RequestBody ProjectDepartmentRequest request) {
        ProjectDepartmentResponse assignedProject = projectAssignmentAPI.assignProjectToDepartment(request);
        return ResponseEntity.ok(assignedProject);
    }

    @PreAuthorize("hasAuthority('ASSIGN_PROJECT_DEPARTMENT')")
    @DeleteMapping("/assignment/{id}")
    ResponseEntity<ProjectDepartmentResponse> removeProjectFromDepartmentById(@PathVariable Long id) {
        ProjectDepartmentResponse removedProject = projectAssignmentAPI.removeProjectFromDepartmentById(id);
        return ResponseEntity.ok(removedProject);
    }

    @PreAuthorize("hasAuthority('READ_DEPARTMENT')")
    @GetMapping("/{projectId}/departments")
    ResponseEntity<List<AssignedDepartmentResponse>> getDepartmentsByProjectId(@PathVariable Long projectId) {
        List<AssignedDepartmentResponse> departmentResponses = projectAssignmentAPI.getDepartmentsByProjectId(projectId);
        return ResponseEntity.ok(departmentResponses);
    }
}
