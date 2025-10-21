package com.jbbcch.smarttaskmanager.task.dto;

import com.jbbcch.smarttaskmanager.task.model.enums.TaskPriority;
import com.jbbcch.smarttaskmanager.task.model.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
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
    @NotNull
    Long projectId;
    UUID actionBy;
}
