package com.jbbcch.smarttaskmanager.security.core.repository;

import com.jbbcch.smarttaskmanager.security.core.model.entity.UserSecurity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserSecurityRepository extends CrudRepository<UserSecurity, UUID> {
    Optional<UserSecurity> findUserByUsername(String username);
}
