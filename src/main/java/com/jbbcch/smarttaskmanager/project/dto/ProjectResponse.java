package com.jbbcch.smarttaskmanager.project.dto;

import com.jbbcch.smarttaskmanager.project.model.enums.ProjectStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectResponse {
    Long id;
    String name;
    String description;
    ProjectStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    UUID createdBy;
    UUID updatedBy;
}
