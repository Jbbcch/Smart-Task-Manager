package com.jbbcch.smarttaskmanager.exceptions;

public class ForeignKeyValidationException extends RuntimeException {
    public ForeignKeyValidationException(String message) {
        super(message);
    }
}
