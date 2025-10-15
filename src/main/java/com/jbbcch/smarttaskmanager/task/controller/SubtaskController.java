package com.jbbcch.smarttaskmanager.task.controller;

import com.jbbcch.smarttaskmanager.task.api.SubtaskInternalAPI;
import com.jbbcch.smarttaskmanager.task.dto.SubtaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.SubtaskResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subtasks")
@RequiredArgsConstructor
public class SubtaskController {

    private final SubtaskInternalAPI subtaskInternalAPI;

    @PostMapping
    public ResponseEntity<SubtaskResponse> createTask(@RequestBody @Valid SubtaskRequest request) {
        SubtaskResponse response = subtaskInternalAPI.createSubtask(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubtaskResponse> updateTask(
            @PathVariable Long id,
            @RequestBody @Valid SubtaskRequest request
    ) {
        SubtaskResponse response = subtaskInternalAPI.updateSubtaskById(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubtaskResponse> deleteTask(@PathVariable Long id) {
        SubtaskResponse response = subtaskInternalAPI.deleteSubtaskById(id);
        return ResponseEntity.ok(response);
    }
}
