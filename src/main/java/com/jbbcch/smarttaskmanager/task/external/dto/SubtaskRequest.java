package com.jbbcch.smarttaskmanager.task.external.dto;

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
public class SubtaskRequest {
    String name;
    String description;
    TaskStatus status;
    UUID createdBy;
    UUID updatedBy;
}
