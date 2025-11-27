package com.jbbcch.smarttaskmanager.project.dto.external;

import com.jbbcch.smarttaskmanager.project.model.enums.ProjectStatus;
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
public class AssignedProjectResponse {
    Long id;
    String name;
    String description;
    ProjectStatus status;
    LocalDateTime assignedAt;
    UUID assignedBy;
}
