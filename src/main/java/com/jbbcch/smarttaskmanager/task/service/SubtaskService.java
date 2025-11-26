package com.jbbcch.smarttaskmanager.task.service;

import com.jbbcch.smarttaskmanager.task.api.SubtaskInternalAPI;
import com.jbbcch.smarttaskmanager.task.api.external.SubtaskExternalAPI;
import com.jbbcch.smarttaskmanager.task.dto.SubtaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.SubtaskResponse;
import com.jbbcch.smarttaskmanager.task.mapper.SubtaskMapper;
import com.jbbcch.smarttaskmanager.task.model.entity.Subtask;
import com.jbbcch.smarttaskmanager.task.model.entity.Task;
import com.jbbcch.smarttaskmanager.task.repository.SubtaskRepository;
import com.jbbcch.smarttaskmanager.task.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubtaskService implements SubtaskInternalAPI, SubtaskExternalAPI {

    private final SubtaskRepository subtaskRepository;
    private final SubtaskMapper subtaskMapper;
    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public SubtaskResponse createSubtask(Long taskId, SubtaskRequest request) {
        Subtask subtask = subtaskMapper.subtaskRequestToSubtask(request);

        Task task = new Task();
        task.setId(taskId);
        subtask.setTask(task);

        subtask.setCreatedBy(request.getActionBy());
        subtask.setDone(false);

        try {
            subtaskRepository.save(subtask);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof ConstraintViolationException cve &&
                    "23503".equals(cve.getSQLState())) {  // postgres foreign key violation
                throw new RuntimeException("Task with id " + taskId + " does not exist");
            }
            throw ex;
        }

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
    @Transactional
    public List<SubtaskResponse> getSubtasksByTaskId(Long taskId) {
        List<Subtask> subtaskList = subtaskRepository.findByTaskId(taskId);

        return subtaskList.stream()
                .map(subtaskMapper::subtaskToSubtaskResponse)
                .toList();
    }

    @Override
    @Transactional
    public SubtaskResponse switchStatusById(Long subtaskId) {
        Subtask subtask = subtaskRepository.findById(subtaskId)
                .orElseThrow(() -> new RuntimeException("Subtask not found"));
        subtask.setDone(!subtask.getDone());
        subtaskRepository.save(subtask);
        return  subtaskMapper.subtaskToSubtaskResponse(subtask);
    }
}
