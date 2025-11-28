package com.jbbcch.smarttaskmanager.task.repository;

import com.jbbcch.smarttaskmanager.security.role.dto.external.AssignedUserResponse;
import com.jbbcch.smarttaskmanager.task.dto.external.AssignedTaskResponse;
import com.jbbcch.smarttaskmanager.task.model.entity.AssignedTask;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AssignedTaskRepository extends CrudRepository<AssignedTask, Long> {
    @Query("""
        SELECT new com.jbbcch.smarttaskmanager.security.role.dto.external.AssignedUserResponse
        (u.id, u.username, u.email, at.assignedAt, at.assignedBy)
        FROM AssignedTask at
        JOIN User u ON at.userId = u.id
        WHERE at.taskId = :taskId
    """)
    List<AssignedUserResponse> findAssignedUsersByTaskId(Long taskId);

    @Query("""
        SELECT new com.jbbcch.smarttaskmanager.task.dto.external.AssignedTaskResponse
        (t.id, t.name, t.status, t.priority,
         t.plannedEndDate, t.actualEndDate, at.assignedAt, at.assignedBy)
        FROM AssignedTask at
        JOIN Task t ON at.taskId = t.id
        WHERE at.userId = :userId
    """)
    List<AssignedTaskResponse> findAssignedTasksByUserId(UUID userId);
}
