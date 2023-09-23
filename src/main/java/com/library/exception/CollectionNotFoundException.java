package com.library.exception;

public class CollectionNotFoundException extends BusinessException {
    public CollectionNotFoundException() {
        super("Collection does not exist");
    }
}