package com.jbbcch.smarttaskmanager.task.dto;

import com.jbbcch.smarttaskmanager.task.model.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubtaskRequest {
    @NotBlank(message = "Subtask name may not be blank")
    String name;
    String description;
    TaskStatus status;
    @NotNull
    Long taskId;
    UUID actionBy;
}
