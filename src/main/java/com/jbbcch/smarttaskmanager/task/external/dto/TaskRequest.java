package com.jbbcch.smarttaskmanager.task.external.dto;

import com.jbbcch.smarttaskmanager.task.model.enums.TaskPriority;
import com.jbbcch.smarttaskmanager.task.model.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
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
public class TaskRequest {
    @NotBlank(message = "Task name may not be blank")
    String name;
    String description;
    TaskStatus status;
    TaskPriority priority;
    LocalDateTime plannedStartDate;
    LocalDateTime plannedEndDate;
    LocalDateTime actualStartDate;
    LocalDateTime actualEndDate;
    UUID actionBy;
}
