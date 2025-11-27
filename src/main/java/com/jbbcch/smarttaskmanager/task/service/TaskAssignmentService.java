package com.jbbcch.smarttaskmanager.task.service;

import com.jbbcch.smarttaskmanager.task.api.TaskAssignmentAPI;
import com.jbbcch.smarttaskmanager.task.dto.AssignedTaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.AssignedTaskResponse;
import com.jbbcch.smarttaskmanager.task.mapper.AssignedTaskMapper;
import com.jbbcch.smarttaskmanager.task.model.entity.AssignedTask;
import com.jbbcch.smarttaskmanager.task.repository.AssignedTaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskAssignmentService implements TaskAssignmentAPI {

    private final AssignedTaskRepository assignedTaskRepository;
    private final AssignedTaskMapper assignedTaskMapper;

    @Override
    @Transactional
    public AssignedTaskResponse assignTaskToUser(AssignedTaskRequest request) {
        AssignedTask AssignedTask = assignedTaskMapper.requestToAssignedTask(request);
        AssignedTask.setAssignedBy(request.getActionBy());

        try {
            assignedTaskRepository.save(AssignedTask);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof ConstraintViolationException cve &&
                    "23503".equals(cve.getSQLState())) {  // postgres foreign key violation
                throw new RuntimeException("Invalid task or user id");
            }
            throw ex;
        }

        return assignedTaskMapper.assignedTaskToResponse(AssignedTask);
    }

    @Override
    @Transactional
    public AssignedTaskResponse removeTaskFromUserById(Long id) {
        AssignedTask removedProject = assignedTaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task-User relation not found"));
        assignedTaskRepository.deleteById(id);
        return assignedTaskMapper.assignedTaskToResponse(removedProject);
    }
}
