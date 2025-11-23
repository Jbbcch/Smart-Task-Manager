package com.jbbcch.smarttaskmanager.security.shared;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    @Query("""
            SELECT DISTINCT a FROM Authority a
            JOIN UserRole ur ON ur.roleId = a.roleId
            WHERE ur.userId = :userId
    """)
    List<Authority> findDistinctAuthoritiesByUserId(UUID userId);

    List<Authority> findAuthoritiesByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);
}
