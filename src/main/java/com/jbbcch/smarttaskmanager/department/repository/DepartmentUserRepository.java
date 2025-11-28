package com.jbbcch.smarttaskmanager.department.repository;

import com.jbbcch.smarttaskmanager.department.model.entity.DepartmentUser;
import com.jbbcch.smarttaskmanager.department.dto.external.AssignedDepartmentResponse;
import com.jbbcch.smarttaskmanager.user.dto.external.AssignedUserResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface DepartmentUserRepository extends CrudRepository<DepartmentUser, Long> {
    @Query("""
        SELECT new com.jbbcch.smarttaskmanager.department.dto.external.AssignedDepartmentResponse
        (d.id, d.name, d.description, du.id, du.assignedAt, du.assignedBy)
        FROM DepartmentUser du
        JOIN Department d ON du.departmentId = d.id
        WHERE du.userId = :userId
    """)
    List<AssignedDepartmentResponse> getDepartmentsByUserId(UUID userId);

    @Query("""
        SELECT new com.jbbcch.smarttaskmanager.user.dto.external.AssignedUserResponse
        (u.id, u.username, u.email, du.id, du.assignedAt, du.assignedBy)
        FROM DepartmentUser du
        JOIN User u ON du.userId = u.id
        WHERE du.departmentId = :departmentId
    """)
    List<AssignedUserResponse> getUsersByDepartmentId(Long departmentId);
}
