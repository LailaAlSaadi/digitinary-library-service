package com.library.exception;

public class CollectionAlreadyBorrowedException extends RuntimeException {
    public CollectionAlreadyBorrowedException() {
        super("Collection is already borrowed");
    }
}
