package com.library.exception;

public class DuplicateCollectionException extends RuntimeException {
    public DuplicateCollectionException() {
        super("Collection with same title already exists");
    }

}