package com.belhard.bookstore.platform.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException (String message) {
        super(message);
    }
}
