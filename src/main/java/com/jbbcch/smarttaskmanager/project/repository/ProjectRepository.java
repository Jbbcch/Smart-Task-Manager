package com.jbbcch.smarttaskmanager.project.repository;

import com.jbbcch.smarttaskmanager.project.model.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
