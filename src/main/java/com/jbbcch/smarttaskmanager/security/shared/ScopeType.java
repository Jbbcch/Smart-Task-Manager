package com.jbbcch.smarttaskmanager.security.shared;

// some illegal combinations may exist, like CREATE_DEPARTMENT_DEPARTMENT...
// or any combination of this with USER besides GLOBAL
public enum ScopeType {
    GLOBAL,
    DEPARTMENT,
    PROJECT
}
