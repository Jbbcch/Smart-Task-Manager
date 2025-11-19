package com.jbbcch.smarttaskmanager.security.role.repository;

import com.jbbcch.smarttaskmanager.security.role.model.entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
}
