package com.jbbcch.smarttaskmanager.task.model.entity;

import com.jbbcch.smarttaskmanager.task.model.enums.TaskPriority;
import com.jbbcch.smarttaskmanager.task.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tasks", schema = "work_management")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Lob
    String description;

    @Enumerated(EnumType.STRING)
    TaskStatus status;

    @Enumerated(EnumType.STRING)
    TaskPriority priority;

    @Column(nullable = false)
    Long projectId;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    List<Subtask> subtasks;

    LocalDateTime plannedEndDate;

    LocalDateTime actualEndDate;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    UUID createdBy;

    UUID updatedBy;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
