package com.belhard.bookstore.exceptions;

public class ApplicationException extends RuntimeException{

    public ApplicationException (String message) {
        super(message);
    }
}
