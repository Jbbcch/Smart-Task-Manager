package com.jbbcch.smarttaskmanager.task.mapper;

import com.jbbcch.smarttaskmanager.task.dto.SubtaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.SubtaskResponse;
import com.jbbcch.smarttaskmanager.task.model.entity.Subtask;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubtaskMapper {
    SubtaskResponse subtaskToSubtaskResponse(Subtask subtask);
    Subtask subtaskRequestToSubtask(SubtaskRequest subtaskRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSubtaskFromRequest(SubtaskRequest request, @MappingTarget Subtask subtask);
}
