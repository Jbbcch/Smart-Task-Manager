package com.jbbcch.smarttaskmanager.task.service;

import com.jbbcch.smarttaskmanager.task.api.SubtaskInternalAPI;
import com.jbbcch.smarttaskmanager.task.api.external.SubtaskExternalAPI;
import com.jbbcch.smarttaskmanager.task.dto.SubtaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.SubtaskResponse;
import com.jbbcch.smarttaskmanager.task.mapper.SubtaskMapper;
import com.jbbcch.smarttaskmanager.task.model.entity.Subtask;
import com.jbbcch.smarttaskmanager.task.repository.SubtaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubtaskService implements SubtaskInternalAPI, SubtaskExternalAPI {

    private final SubtaskRepository subtaskRepository;
    private final SubtaskMapper subtaskMapper;

    @Override
    @Transactional
    public SubtaskResponse createSubtask(SubtaskRequest request) {
        Subtask subtask = subtaskMapper.subtaskRequestToSubtask(request);
        subtask.setCreatedBy(request.getActionBy());
        subtaskRepository.save(subtask);
        return subtaskMapper.subtaskToSubtaskResponse(subtask);
    }

    @Override
    @Transactional
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
    @Transactional
    public SubtaskResponse deleteSubtaskById(Long id) {
        Subtask deletedSubtask = subtaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subtask not found"));
        subtaskRepository.deleteById(id);
        return subtaskMapper.subtaskToSubtaskResponse(deletedSubtask);
    }

    @Override
    public List<SubtaskResponse> getSubtasksByTaskId(Long taskId) {
        List<Subtask> subtaskList = subtaskRepository.findByTaskId(taskId);

        return subtaskList.stream()
                .map(subtaskMapper::subtaskToSubtaskResponse)
                .toList();
    }
}
