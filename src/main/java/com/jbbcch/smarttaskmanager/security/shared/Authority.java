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
    Long id;

    Long roleId;

    @Enumerated(EnumType.STRING)
    String permission;

    @Enumerated(EnumType.STRING)
    ScopeType scope;

    Long scopeId;

    @Override
    public String toString() {
        return permission + "_" + scope + ":" + scopeId;
    }
}
