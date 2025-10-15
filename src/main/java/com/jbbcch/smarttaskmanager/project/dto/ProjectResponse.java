package com.jbbcch.smarttaskmanager.project.dto;

import com.jbbcch.smarttaskmanager.project.model.enums.ProjectStatus;
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
public class ProjectResponse {
    Long id;
    String name;
    ProjectStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    UUID createdBy;
    UUID updatedBy;
}
