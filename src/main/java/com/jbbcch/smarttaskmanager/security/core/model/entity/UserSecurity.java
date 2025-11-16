package com.jbbcch.smarttaskmanager.security.core.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "organization_management")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserSecurity implements UserDetails {
    @Id
    UUID id;

    String username;

    String password;

    @Transient
    List<GrantedAuthority> authorities;

    Boolean isDeleted;

    @Override
    public boolean isEnabled() {
        return !isDeleted;
    }
}
