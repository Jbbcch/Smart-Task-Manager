package com.jbbcch.smarttaskmanager.security.role.repository;

import com.jbbcch.smarttaskmanager.security.role.dto.external.AssignedRolesResponse;
import com.jbbcch.smarttaskmanager.security.role.model.entity.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    @Query("""
        SELECT new AssignedRolesResponse(r.id, r.name, r.description, ur.assignedAt, ur.assignedBy)
        FROM UserRole ur
        JOIN Role r ON ur.roleId = r.id
        WHERE ur.userId = :userId
    """)
    List<AssignedRolesResponse> findAssignedRolesByUserId(UUID userId);
}
