package com.jbbcch.smarttaskmanager.department.controller;

import com.jbbcch.smarttaskmanager.department.api.DepartmentInternalAPI;
import com.jbbcch.smarttaskmanager.department.dto.DepartmentRequest;
import com.jbbcch.smarttaskmanager.department.dto.DepartmentResponse;
import com.jbbcch.smarttaskmanager.project.api.external.ProjectAssignmentExternalAPI;
import com.jbbcch.smarttaskmanager.project.dto.external.AssignedProjectResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentInternalAPI departmentInternalAPI;
    private final ProjectAssignmentExternalAPI projectAssignmentExternalAPI;

    @PreAuthorize("hasAuthority('READ_DEPARTMENT')")
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id) {
        DepartmentResponse department = departmentInternalAPI.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @PreAuthorize("hasAuthority('CREATE_DEPARTMENT')")
    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(@RequestBody @Valid DepartmentRequest request) {
        DepartmentResponse createdDepartment = departmentInternalAPI.createDepartment(request);
        return ResponseEntity.ok(createdDepartment);
    }

    @PreAuthorize("hasAuthority('UPDATE_DEPARTMENT')")
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartmentById(
            @PathVariable Long id,
            @RequestBody @Valid DepartmentRequest request
    ) {
        DepartmentResponse updatedDepartment = departmentInternalAPI.updateDepartmentById(id, request);
        return ResponseEntity.ok(updatedDepartment);
    }

    @PreAuthorize("hasAuthority('DELETE_DEPARTMENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentResponse> deleteDepartmentById(@PathVariable Long id) {
        DepartmentResponse deletedDepartment = departmentInternalAPI.deleteDepartmentById(id);
        return ResponseEntity.ok(deletedDepartment);
    }

    @PreAuthorize("hasAuthority('READ_PROJECT')")
    @GetMapping("/{id}/projects")
    public ResponseEntity<List<AssignedProjectResponse>> getDepartmentProjects(@PathVariable Long departmentId) {
        List<AssignedProjectResponse> assignedProjects = projectAssignmentExternalAPI.getProjectsByDepartmentId(departmentId);
        return ResponseEntity.ok(assignedProjects);
    }
}
