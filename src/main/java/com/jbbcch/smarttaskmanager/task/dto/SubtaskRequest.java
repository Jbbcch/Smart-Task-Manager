package com.jbbcch.smarttaskmanager.task.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubtaskRequest {
    @NotBlank(message = "Subtask name may not be blank")
    String name;
    String description;
    UUID actionBy;
}
