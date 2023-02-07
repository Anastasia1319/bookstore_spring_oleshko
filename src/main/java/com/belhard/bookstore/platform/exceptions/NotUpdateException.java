package com.belhard.bookstore.platform.exceptions;

public class NotUpdateException extends RuntimeException{

    public NotUpdateException (String message) {
        super(message);
    }

    public NotUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
