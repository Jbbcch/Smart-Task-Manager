package com.jbbcch.smarttaskmanager.security.role.repository;

import com.jbbcch.smarttaskmanager.user.dto.external.AssignedUserResponse;
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
        SELECT new com.jbbcch.smarttaskmanager.security.role.dto.external.AssignedRolesResponse
        (r.id, r.name, r.description, ur.id, ur.assignedAt, ur.assignedBy)
        FROM UserRole ur
        JOIN Role r ON ur.roleId = r.id
        WHERE ur.userId = :userId
    """)
    List<AssignedRolesResponse> findAssignedRolesByUserId(UUID userId);

    @Query("""
        SELECT new com.jbbcch.smarttaskmanager.user.dto.external.AssignedUserResponse
        (u.id, u.username, u.email, ur.id, ur.assignedAt, ur.assignedBy)
        FROM UserRole ur
        JOIN User u ON ur.userId = u.id
        WHERE ur.roleId = :roleId
    """)
    List<AssignedUserResponse> findAssignedUsersByRoleId(Long roleId);
}
