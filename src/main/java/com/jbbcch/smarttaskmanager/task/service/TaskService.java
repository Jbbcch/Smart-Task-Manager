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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService implements TaskExternalAPI {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponse createTask(TaskRequest request) {
        Task task = taskMapper.taskRequestToTask(request);
        task.setCreatedBy(request.getActionBy());
        taskRepository.save(task);
        return taskMapper.taskToTaskResponse(task);
    }

    @Override
    public TaskResponse updateTaskById(Long id, TaskRequest request) {
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
    public List<TaskResponse> getTasksByProjectId(Long id) {
        List<Task> taskList = taskRepository.findByProjectId(id);
        return taskList.stream()
                .map(taskMapper::taskToTaskResponse)
                .toList();
    }
}
