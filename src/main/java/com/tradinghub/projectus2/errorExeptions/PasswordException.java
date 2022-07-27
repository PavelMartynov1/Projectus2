package com.tradinghub.projectus2.errorExeptions;

public class PasswordException extends RuntimeException{
    public PasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordException(String message) {
        super(message);
    }
}
