package com.jbbcch.smarttaskmanager.department.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentRequest {
    @NotBlank(message = "Department name may not be blank")
    String name;
    String description;
    UUID actionBy;
}
