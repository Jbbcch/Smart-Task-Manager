package com.jbbcch.smarttaskmanager.security.shared;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "role_permissions", schema = "organization_management")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long roleId;

    @Column(nullable = false)
    String permission;

    @Enumerated(EnumType.STRING)
    ScopeType scope;

    Long scopeId;

    @Override
    public String toString() {
        return permission + "_" + scope + ":" + scopeId;
    }

    public static Authority fromString(String authorityString) {
        try {
            String[] parts = authorityString.split("_");

            // crude way of checking whether permission is valid;
            // also the concept of converting a string to an object and back
            // just to check its validity and still have only 1 line of code
            // is so funny to me that I'm gonna just:
            String permission = Permission.fromString(parts[0]).toString();
            // leave this as is. it's so stupid, I love it.

            String[] scopePart = parts[1].split(":");

            ScopeType scope = ScopeType.valueOf(scopePart[0]);
            long scopeId = Long.parseLong(scopePart[1]);

            Authority newAuthority = new Authority();
            newAuthority.permission = permission;
            newAuthority.scope = scope;
            newAuthority.scopeId = scopeId;

            return newAuthority;
        } catch (Exception e) {
            throw new RuntimeException("Invalid authority string");
        }
    }
}
