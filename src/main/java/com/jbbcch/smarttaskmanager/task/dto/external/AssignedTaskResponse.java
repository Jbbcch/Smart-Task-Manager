package com.jbbcch.smarttaskmanager.task.dto.external;

import com.jbbcch.smarttaskmanager.task.model.enums.TaskPriority;
import com.jbbcch.smarttaskmanager.task.model.enums.TaskStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AssignedTaskResponse {
    Long id;
    String name;
    TaskStatus status;
    TaskPriority priority;
    LocalDateTime plannedEndDate;
    LocalDateTime actualEndDate;
    LocalDateTime assignedAt;
    UUID assignedBy;
}
