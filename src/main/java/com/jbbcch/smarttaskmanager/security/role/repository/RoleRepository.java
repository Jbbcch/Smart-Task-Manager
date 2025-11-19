package com.jbbcch.smarttaskmanager.security.role.repository;

import com.jbbcch.smarttaskmanager.security.role.model.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findRoleByName(String name);
}
