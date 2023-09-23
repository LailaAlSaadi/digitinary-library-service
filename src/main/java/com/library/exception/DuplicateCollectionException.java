package com.library.exception;

public class DuplicateCollectionException extends BusinessException {
    public DuplicateCollectionException() {
        super("Collection with same title already exists");
    }

}