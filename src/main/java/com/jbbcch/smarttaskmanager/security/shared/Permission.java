package com.jbbcch.smarttaskmanager.security.shared;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Permission {

    public enum Action {
        CREATE, READ, UPDATE, DELETE
    }

    public enum Resource {
        DEPARTMENT, USER, ROLE, PROJECT, TASK, SUBTASK
    }

    private final Action action;
    private final Resource resource;

    @Override
    public String toString() {
        return action.name() + "_" + resource.name();
    }

    public static Permission fromString(String permissionString) {
        try {
            String[] parts = permissionString.split("_");
            Action action = Action.valueOf(parts[0]);
            Resource resource = Resource.valueOf(parts[1]);
            return new Permission(action, resource);
        } catch (Exception e) {
            throw new RuntimeException("Invalid permission string");
        }
    }
}
