package com.jbbcch.smarttaskmanager.department.dto;

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
public class DepartmentUserResponse {
    Long id;
    Long departmentId;
    UUID userId;
    LocalDateTime assignedAt;
    UUID assignedBy;
}
