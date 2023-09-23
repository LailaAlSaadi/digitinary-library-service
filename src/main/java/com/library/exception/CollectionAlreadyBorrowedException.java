package com.library.exception;

public class CollectionAlreadyBorrowedException extends BusinessException {
    public CollectionAlreadyBorrowedException() {
        super("Collection is already borrowed");
    }
}
