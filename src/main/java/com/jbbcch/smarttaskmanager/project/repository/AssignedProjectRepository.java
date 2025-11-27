package com.jbbcch.smarttaskmanager.project.repository;

import com.jbbcch.smarttaskmanager.project.dto.external.AssignedProjectResponse;
import com.jbbcch.smarttaskmanager.project.model.entity.AssignedProject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignedProjectRepository extends CrudRepository<AssignedProject, Long> {
    @Query("""
        SELECT new com.jbbcch.smarttaskmanager.project.dto.external.AssignedProjectResponse
        (p.id, p.name, p.description, p.status, ap.assignedAt, ap.assignedBy)
        FROM AssignedProject ap
        JOIN Project p ON ap.projectId = p.id
        WHERE ap.departmentId = :departmentId
    """)
    List<AssignedProjectResponse> getProjectsByDepartmentId(Long departmentId);
}
