package com.jbbcch.smarttaskmanager.task.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "assigned_tasks", schema = "work_management")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AssignedTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long taskId;

    @Column(nullable = false)
    UUID userId;

    @Column(nullable = false)
    UUID assignedBy;

    @Column(nullable = false)
    LocalDateTime assignedAt;

    @PrePersist
    public void prePersist() {
        this.assignedAt = LocalDateTime.now();
    }
}
