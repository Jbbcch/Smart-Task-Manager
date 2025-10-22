package com.jbbcch.smarttaskmanager.department.controller;

import com.jbbcch.smarttaskmanager.department.api.DepartmentInternalAPI;
import com.jbbcch.smarttaskmanager.department.dto.DepartmentRequest;
import com.jbbcch.smarttaskmanager.department.dto.DepartmentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentInternalAPI departmentInternalAPI;

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id) {
        DepartmentResponse department = departmentInternalAPI.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(@RequestBody @Valid DepartmentRequest request) {
        DepartmentResponse createdDepartment = departmentInternalAPI.createDepartment(request);
        return ResponseEntity.ok(createdDepartment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartmentById(
            @PathVariable Long id,
            @RequestBody @Valid DepartmentRequest request
    ) {
        DepartmentResponse updatedDepartment = departmentInternalAPI.updateDepartmentById(id, request);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DepartmentResponse> deleteDepartmentById(@PathVariable Long id) {
        DepartmentResponse deletedDepartment = departmentInternalAPI.deleteDepartmentById(id);
        return ResponseEntity.ok(deletedDepartment);
    }
}
