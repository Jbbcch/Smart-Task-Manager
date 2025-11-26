package com.jbbcch.smarttaskmanager.task.service;

import com.jbbcch.smarttaskmanager.task.api.TaskAssignmentAPI;
import com.jbbcch.smarttaskmanager.task.api.TaskInternalAPI;
import com.jbbcch.smarttaskmanager.task.api.external.TaskExternalAPI;
import com.jbbcch.smarttaskmanager.task.dto.AssignedTaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.AssignedTaskResponse;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskResponse;
import com.jbbcch.smarttaskmanager.task.mapper.AssignedTaskMapper;
import com.jbbcch.smarttaskmanager.task.mapper.TaskMapper;
import com.jbbcch.smarttaskmanager.task.model.entity.AssignedTask;
import com.jbbcch.smarttaskmanager.task.model.entity.Task;
import com.jbbcch.smarttaskmanager.task.repository.AssignedTaskRepository;
import com.jbbcch.smarttaskmanager.task.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements TaskInternalAPI, TaskExternalAPI, TaskAssignmentAPI {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final AssignedTaskRepository assignedTaskRepository;
    private final AssignedTaskMapper assignedTaskMapper;

    @Override
    @Transactional
    public TaskResponse createTask(Long projectId, TaskRequest request) {
        Task task = taskMapper.taskRequestToTask(request);
        task.setCreatedBy(request.getActionBy());
        task.setProjectId(projectId);

        try {
            taskRepository.save(task);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof ConstraintViolationException cve &&
                    "23503".equals(cve.getSQLState())) {  // postgres foreign key violation
                throw new RuntimeException("Project with id " + task.getProjectId() + " does not exist");
            }
            throw ex;
        }

        return taskMapper.taskToTaskResponse(task);
    }

    @Override
    @Transactional
    public TaskResponse updateTaskById(Long id, TaskRequest request) {
        taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        Task task = taskMapper.taskRequestToTask(request);
        task.setId(id);
        task.setUpdatedBy(request.getActionBy());
        taskRepository.save(task);
        return taskMapper.taskToTaskResponse(task);
    }

    @Override
    @Transactional
    public TaskResponse deleteTaskById(Long id) {
        Task deletedTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.deleteById(id);
        return taskMapper.taskToTaskResponse(deletedTask);
    }

    @Override
    @Transactional
    public List<TaskResponse> getTasksByProjectId(Long projectId) {
        List<Task> taskList = taskRepository.findByProjectId(projectId);

        return taskList.stream()
                .map(task -> {
                    TaskResponse taskResponse = taskMapper.taskToTaskResponse(task);
                    taskResponse.setSubtaskCount(getSubtaskCountByTask(task));
                    return taskResponse;
                }).toList();
    }

    public Integer getSubtaskCountByTask(Task task) {
        return task.getSubtasks().size();
    }

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
