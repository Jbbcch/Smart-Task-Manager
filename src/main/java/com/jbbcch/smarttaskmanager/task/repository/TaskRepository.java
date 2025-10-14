package com.jbbcch.smarttaskmanager.task.repository;

import com.jbbcch.smarttaskmanager.task.model.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);
}
