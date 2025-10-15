package com.jbbcch.smarttaskmanager.task.service;

import com.jbbcch.smarttaskmanager.task.external.api.SubtaskExternalAPI;
import com.jbbcch.smarttaskmanager.task.external.dto.SubtaskRequest;
import com.jbbcch.smarttaskmanager.task.external.dto.SubtaskResponse;
import com.jbbcch.smarttaskmanager.task.mapper.SubtaskMapper;
import com.jbbcch.smarttaskmanager.task.model.entity.Subtask;
import com.jbbcch.smarttaskmanager.task.repository.SubtaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubtaskService implements SubtaskExternalAPI {

    private final SubtaskRepository subtaskRepository;
    private final SubtaskMapper subtaskMapper;

    @Override
    public SubtaskResponse createSubtaskForTaskId(Long taskId, SubtaskRequest request) {
        Subtask subtask = subtaskMapper.subtaskRequestToSubtask(request);
        subtask.setCreatedBy(request.getActionBy());
        subtaskRepository.save(subtask);
        return subtaskMapper.subtaskToSubtaskResponse(subtask);
    }

    @Override
    public SubtaskResponse updateSubtaskById(Long id, SubtaskRequest request) {
        subtaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subtask not found"));
        Subtask subtask = subtaskMapper.subtaskRequestToSubtask(request);
        subtask.setId(id);
        subtask.setUpdatedBy(request.getActionBy());
        subtaskRepository.save(subtask);
        return subtaskMapper.subtaskToSubtaskResponse(subtask);
    }

    @Override
    public SubtaskResponse deleteSubtaskById(Long id) {
        Subtask deletedSubtask = subtaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subtask not found"));
        subtaskRepository.deleteById(id);
        return subtaskMapper.subtaskToSubtaskResponse(deletedSubtask);
    }
}
