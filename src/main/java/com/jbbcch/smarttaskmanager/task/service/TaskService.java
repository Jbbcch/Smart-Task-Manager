package com.jbbcch.smarttaskmanager.task.service;

import com.jbbcch.smarttaskmanager.task.api.TaskInternalAPI;
import com.jbbcch.smarttaskmanager.task.api.external.TaskExternalAPI;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskResponse;
import com.jbbcch.smarttaskmanager.task.mapper.TaskMapper;
import com.jbbcch.smarttaskmanager.task.model.entity.Task;
import com.jbbcch.smarttaskmanager.task.model.enums.TaskStatus;
import com.jbbcch.smarttaskmanager.task.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements TaskInternalAPI, TaskExternalAPI {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

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
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return taskMapper.taskToTaskResponse(task);
    }

    @Override
    @Transactional
    public TaskResponse changeTaskStatus(Long id, TaskStatus taskStatus) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(taskStatus);
        taskRepository.save(task);
        return taskMapper.taskToTaskResponse(task);
    }

    @Override
    @Transactional
    public TaskResponse updateTaskById(Long id, TaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        taskMapper.updateTaskFromRequest(request, task);
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
}
