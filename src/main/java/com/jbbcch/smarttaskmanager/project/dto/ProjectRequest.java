package com.jbbcch.smarttaskmanager.project.dto;

import com.jbbcch.smarttaskmanager.project.model.enums.ProjectStatus;
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
public class ProjectRequest {
    @NotBlank(message = "Project name may not be blank")
    String name;
    String description;
    ProjectStatus status;
    UUID actionBy;
}
