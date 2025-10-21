package com.jbbcch.smarttaskmanager.department.dto;

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
public class DepartmentResponse {
    Long id;
    String name;
    String description;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    UUID createdBy;
    UUID updatedBy;
}
