package com.tradinghub.projectus2.errorExeptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
