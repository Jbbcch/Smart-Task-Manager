package com.jbbcch.smarttaskmanager.task.service;

import com.jbbcch.smarttaskmanager.task.external.api.TaskExternalAPI;
import com.jbbcch.smarttaskmanager.task.external.dto.TaskRequest;
import com.jbbcch.smarttaskmanager.task.external.dto.TaskResponse;
import com.jbbcch.smarttaskmanager.task.mapper.TaskMapper;
import com.jbbcch.smarttaskmanager.task.model.entity.Task;
import com.jbbcch.smarttaskmanager.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements TaskExternalAPI {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponse createTaskForProjectId(Long projectId, TaskRequest request) {
        Task task = taskMapper.taskRequestToTask(request);
        task.setCreatedBy(request.getActionBy());
        taskRepository.save(task);
        return taskMapper.taskToTaskResponse(task);
    }

    @Override
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
