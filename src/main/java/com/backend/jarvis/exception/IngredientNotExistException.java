package com.backend.jarvis.exception;

public class IngredientNotExistException extends RuntimeException {
    public IngredientNotExistException() {
    }

    public IngredientNotExistException(String message) {
        super(message);
    }

    public IngredientNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public IngredientNotExistException(Throwable cause) {
        super(cause);
    }

    public IngredientNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
