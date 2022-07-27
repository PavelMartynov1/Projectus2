package com.tradinghub.projectus2.errorExeptions;

public class WrongCodeException  extends RuntimeException{
    public WrongCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongCodeException(String message) {
        super(message);
    }
}
