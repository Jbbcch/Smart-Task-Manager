package com.jbbcch.smarttaskmanager.project.repository;

import com.jbbcch.smarttaskmanager.project.model.entity.AssignedProject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignedProjectRepository extends CrudRepository<AssignedProject, Long> {
}
