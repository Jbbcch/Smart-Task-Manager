package com.jbbcch.smarttaskmanager.task.mapper;

import com.jbbcch.smarttaskmanager.task.dto.SubtaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.SubtaskResponse;
import com.jbbcch.smarttaskmanager.task.model.entity.Subtask;
import com.jbbcch.smarttaskmanager.task.model.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubtaskMapper {
    SubtaskResponse subtaskToSubtaskResponse(Subtask subtask);
    Subtask subtaskRequestToSubtask(SubtaskRequest subtaskRequest);

    default Task map(Long taskId) {
        if (taskId == null) return null;
        Task task = new Task();
        task.setId(taskId);
        return task;
    }
}
