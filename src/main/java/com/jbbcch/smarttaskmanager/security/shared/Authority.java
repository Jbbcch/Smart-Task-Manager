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

    @Enumerated(EnumType.STRING)
    Permission permission;

    public static Authority fromString(String authorityString) {
        try {
            Permission permission = Permission.valueOf(authorityString);

            Authority newAuthority = new Authority();
            newAuthority.permission = permission;

            return newAuthority;
        } catch (Exception e) {
            throw new RuntimeException("Invalid authority string");
        }
    }
}
