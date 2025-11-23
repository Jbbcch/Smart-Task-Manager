package com.jbbcch.smarttaskmanager.task.repository;

import com.jbbcch.smarttaskmanager.task.model.entity.AssignedTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignedTaskRepository extends CrudRepository<AssignedTask, Long> {
}
