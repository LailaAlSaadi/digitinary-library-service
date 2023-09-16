package com.library.collection.exception;

public class DuplicateCollectionException extends RuntimeException {
    public DuplicateCollectionException() {
        super("Collection with same title already exists");
    }

}