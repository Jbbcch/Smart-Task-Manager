package com.jbbcch.smarttaskmanager.project.dto;

import com.jbbcch.smarttaskmanager.project.model.enums.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectRequest {
    @NotBlank(message = "Project name may not be blank")
    String name;
    String description;
    ProjectStatus status;
    UUID actionBy;
}
