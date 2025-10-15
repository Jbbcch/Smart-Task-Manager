package com.jbbcch.smarttaskmanager.task.dto;

import com.jbbcch.smarttaskmanager.task.model.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubtaskResponse {
    Long id;
    String name;
    String description;
    TaskStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    UUID createdBy;
    UUID updatedBy;
}