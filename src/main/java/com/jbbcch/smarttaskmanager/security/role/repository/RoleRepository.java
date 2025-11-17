package com.jbbcch.smarttaskmanager.security.role.repository;

import com.jbbcch.smarttaskmanager.security.role.model.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
