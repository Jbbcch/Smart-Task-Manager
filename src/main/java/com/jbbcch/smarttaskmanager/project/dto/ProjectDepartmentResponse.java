package com.jbbcch.smarttaskmanager.project.dto;

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
public class ProjectDepartmentResponse {
    Long id;
    Long projectId;
    Long departmentId;
    UUID assignedBy;
    LocalDateTime assignedAt;
}
