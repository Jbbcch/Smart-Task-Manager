package com.jbbcch.smarttaskmanager.task.repository;

import com.jbbcch.smarttaskmanager.task.model.entity.Subtask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtaskRepository extends CrudRepository<Subtask, Long> {
}
