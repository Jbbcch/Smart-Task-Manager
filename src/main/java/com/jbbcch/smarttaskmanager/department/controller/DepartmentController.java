package com.jbbcch.smarttaskmanager.department.controller;

import com.jbbcch.smarttaskmanager.department.api.DepartmentAssignmentAPI;
import com.jbbcch.smarttaskmanager.department.api.DepartmentInternalAPI;
import com.jbbcch.smarttaskmanager.department.dto.DepartmentRequest;
import com.jbbcch.smarttaskmanager.department.dto.DepartmentResponse;
import com.jbbcch.smarttaskmanager.department.dto.DepartmentUserRequest;
import com.jbbcch.smarttaskmanager.department.dto.DepartmentUserResponse;
import com.jbbcch.smarttaskmanager.project.api.external.ProjectAssignmentExternalAPI;
import com.jbbcch.smarttaskmanager.project.dto.external.AssignedProjectResponse;
import com.jbbcch.smarttaskmanager.user.dto.external.AssignedUserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final DepartmentAssignmentAPI departmentAssignmentAPI;

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
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
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
    @GetMapping("/{departmentId}/projects")
    public ResponseEntity<List<AssignedProjectResponse>> getDepartmentProjects(@PathVariable Long departmentId) {
        List<AssignedProjectResponse> assignedProjects = projectAssignmentExternalAPI.getProjectsByDepartmentId(departmentId);
        return ResponseEntity.ok(assignedProjects);
    }

    @PreAuthorize("hasAuthority('ASSIGN_USER_DEPARTMENT')")
    @PostMapping("/assignment")
    public ResponseEntity<DepartmentUserResponse> assignUserToDepartment(@RequestBody DepartmentUserRequest request) {
        DepartmentUserResponse response = departmentAssignmentAPI.assignUserToDepartment(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ASSIGN_USER_DEPARTMENT')")
    @DeleteMapping("/assignment/{id}")
    public ResponseEntity<DepartmentUserResponse> removeUserFromDepartment(@RequestBody Long id) {
        DepartmentUserResponse response = departmentAssignmentAPI.removeUserFromDepartmentById(id);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping("/{departmentId}/users")
    public ResponseEntity<List<AssignedUserResponse>> getUsersByDepartmentId(@PathVariable Long departmentId) {
        List<AssignedUserResponse> responseList = departmentAssignmentAPI.getUsersByDepartmentId(departmentId);
        return ResponseEntity.ok(responseList);
    }
}
