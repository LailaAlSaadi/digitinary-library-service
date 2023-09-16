package com.library.exception;

public class CollectionNotFoundException extends RuntimeException {
    public CollectionNotFoundException() {
        super("Collection does not exist");
    }
}