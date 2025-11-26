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
    public ResponseEntity<SubtaskResponse> createSubtask(
            @RequestParam Long id,
            @RequestBody @Valid SubtaskRequest request
    ) {
        SubtaskResponse createdSubtask = subtaskInternalAPI.createSubtask(id, request);
        return ResponseEntity.ok(createdSubtask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubtaskResponse> updateSubtask(
            @PathVariable Long id,
            @RequestBody @Valid SubtaskRequest request
    ) {
        SubtaskResponse updatedSubtask = subtaskInternalAPI.updateSubtaskById(id, request);
        return ResponseEntity.ok(updatedSubtask);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SubtaskResponse> setSubtaskStatus(@PathVariable Long id) {
        SubtaskResponse subtaskResponse = subtaskInternalAPI.switchStatusById(id);
        return ResponseEntity.ok(subtaskResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubtaskResponse> deleteSubtask(@PathVariable Long id) {
        SubtaskResponse deletedSubtask = subtaskInternalAPI.deleteSubtaskById(id);
        return ResponseEntity.ok(deletedSubtask);
    }
}
