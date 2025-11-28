package com.jbbcch.smarttaskmanager.task.service;

import com.jbbcch.smarttaskmanager.exceptions.ForeignKeyValidationException;
import com.jbbcch.smarttaskmanager.exceptions.ResourceNotFoundException;
import com.jbbcch.smarttaskmanager.user.dto.external.AssignedUserResponse;
import com.jbbcch.smarttaskmanager.task.api.TaskAssignmentAPI;
import com.jbbcch.smarttaskmanager.task.api.external.TaskAssignmentExternalAPI;
import com.jbbcch.smarttaskmanager.task.dto.TaskUserRequest;
import com.jbbcch.smarttaskmanager.task.dto.TaskUserResponse;
import com.jbbcch.smarttaskmanager.task.dto.external.AssignedTaskResponse;
import com.jbbcch.smarttaskmanager.task.mapper.AssignedTaskMapper;
import com.jbbcch.smarttaskmanager.task.model.entity.AssignedTask;
import com.jbbcch.smarttaskmanager.task.repository.AssignedTaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskAssignmentService implements TaskAssignmentAPI, TaskAssignmentExternalAPI {

    private final AssignedTaskRepository assignedTaskRepository;
    private final AssignedTaskMapper assignedTaskMapper;

    @Override
    @Transactional
    public TaskUserResponse assignTaskToUser(TaskUserRequest request) {
        AssignedTask assignedTask = assignedTaskMapper.requestToAssignedTask(request);
        assignedTask.setAssignedBy(request.getActionBy());

        try {
            assignedTaskRepository.save(assignedTask);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof ConstraintViolationException cve &&
                    "23503".equals(cve.getSQLState())) {  // postgres foreign key violation
                throw new ForeignKeyValidationException("Invalid task or user id");
            }
            throw ex;
        }

        return assignedTaskMapper.assignedTaskToResponse(assignedTask);
    }

    @Override
    @Transactional
    public TaskUserResponse removeTaskFromUserById(Long id) {
        AssignedTask removedTask = assignedTaskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task-User relation not found"));
        assignedTaskRepository.deleteById(id);
        return assignedTaskMapper.assignedTaskToResponse(removedTask);
    }

    @Override
    public List<AssignedUserResponse> getUsersByTaskId(Long taskId) {
        return assignedTaskRepository.findAssignedUsersByTaskId(taskId);
    }

    @Override
    public List<AssignedTaskResponse> getTasksByUserId(UUID userId) {
        return assignedTaskRepository.findAssignedTasksByUserId(userId);
    }
}
