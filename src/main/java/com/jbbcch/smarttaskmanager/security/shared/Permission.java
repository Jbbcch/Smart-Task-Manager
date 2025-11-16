package com.jbbcch.smarttaskmanager.security.shared;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "permissions", schema = "organization_management")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Permission {
    @Id
    Long id;

    String action;

    String resource;

    @Override
    public String toString() {
        return action + "_" + resource;
    }
}
