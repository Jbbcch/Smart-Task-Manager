package com.jbbcch.smarttaskmanager.task.service;

import com.jbbcch.smarttaskmanager.task.api.TaskInternalAPI;
import com.jbbcch.smarttaskmanager.task.api.external.TaskExternalAPI;
import com.jbbcch.smarttaskmanager.task.dto.TaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.external.TaskResponse;
import com.jbbcch.smarttaskmanager.task.mapper.TaskMapper;
import com.jbbcch.smarttaskmanager.task.model.entity.Task;
import com.jbbcch.smarttaskmanager.task.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements TaskInternalAPI, TaskExternalAPI {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    @Transactional
    public TaskResponse createTask(TaskRequest request) {
        Task task = taskMapper.taskRequestToTask(request);
        task.setCreatedBy(request.getActionBy());
        taskRepository.save(task);
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
    public List<TaskResponse> getTasksByProjectId(Long projectId) {
        List<Task> taskList = taskRepository.findByProjectId(projectId);
        return taskList.stream()
                .map(taskMapper::taskToTaskResponse)
                .toList();
    }
}
