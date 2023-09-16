package com.library.collection.exception;

public class CollectionNotFoundException extends RuntimeException {
    public CollectionNotFoundException() {
        super("Collection does not exist");
    }
}