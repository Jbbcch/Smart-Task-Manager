package com.jbbcch.smarttaskmanager.task.external.dto;

import com.jbbcch.smarttaskmanager.task.model.enums.TaskPriority;
import com.jbbcch.smarttaskmanager.task.model.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponse {
    Long id;
    String name;
    String description;
    TaskStatus status;
    TaskPriority priority;
    List<SubtaskResponse> subtasks;
    LocalDateTime plannedStartDate;
    LocalDateTime plannedEndDate;
    LocalDateTime actualStartDate;
    LocalDateTime actualEndDate;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    UUID createdBy;
    UUID updatedBy;
}