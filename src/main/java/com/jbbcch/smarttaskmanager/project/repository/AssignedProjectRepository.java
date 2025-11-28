package com.jbbcch.smarttaskmanager.project.repository;

import com.jbbcch.smarttaskmanager.department.dto.external.AssignedDepartmentResponse;
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
        (p.id, p.name, p.description, p.status, ap.id, ap.assignedAt, ap.assignedBy)
        FROM AssignedProject ap
        JOIN Project p ON ap.projectId = p.id
        WHERE ap.departmentId = :departmentId
    """)
    List<AssignedProjectResponse> getProjectsByDepartmentId(Long departmentId);

    @Query("""
        SELECT new com.jbbcch.smarttaskmanager.department.dto.external.AssignedDepartmentResponse
        (d.id, d.name, d.description, ap.id, ap.assignedAt, ap.assignedBy)
        FROM AssignedProject ap
        JOIN Department d ON ap.departmentId = d.id
        WHERE ap.projectId = :projectId
    """)
    List<AssignedDepartmentResponse> getDepartmentsByProjectId(Long projectId);
}
