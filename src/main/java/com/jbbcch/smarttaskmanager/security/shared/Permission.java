package com.jbbcch.smarttaskmanager.security.shared;

public enum Permission {
    CREATE_TASK,
    UPDATE_TASK,
    DELETE_TASK,
    // no READ_TASK so that all users can read tasks by default
    ASSIGN_TASK_USER,

    CREATE_PROJECT,
    UPDATE_PROJECT,
    READ_PROJECT,
    DELETE_PROJECT,
    ASSIGN_PROJECT_DEPARTMENT,

    CREATE_ROLE,
    UPDATE_ROLE,
    READ_ROLE,
    DELETE_ROLE,
    ASSIGN_ROLE_USER,

    CREATE_DEPARTMENT,
    UPDATE_DEPARTMENT,
    READ_DEPARTMENT,
    DELETE_DEPARTMENT,

    CREATE_USER,
    UPDATE_USER,
    READ_USER,
    DELETE_USER,
    ASSIGN_USER_DEPARTMENT,
}
