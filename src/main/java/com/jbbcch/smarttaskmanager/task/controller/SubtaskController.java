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
        SubtaskResponse createdSubtask = subtaskInternalAPI.createSubtask(request);
        return ResponseEntity.ok(createdSubtask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubtaskResponse> updateTask(
            @PathVariable Long id,
            @RequestBody @Valid SubtaskRequest request
    ) {
        SubtaskResponse updatedSubtask = subtaskInternalAPI.updateSubtaskById(id, request);
        return ResponseEntity.ok(updatedSubtask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubtaskResponse> deleteTask(@PathVariable Long id) {
        SubtaskResponse deletedSubtask = subtaskInternalAPI.deleteSubtaskById(id);
        return ResponseEntity.ok(deletedSubtask);
    }
}
