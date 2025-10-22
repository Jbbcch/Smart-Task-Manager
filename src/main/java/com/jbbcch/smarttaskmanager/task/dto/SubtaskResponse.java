package com.jbbcch.smarttaskmanager.task.dto;

import com.jbbcch.smarttaskmanager.task.model.enums.TaskStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
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