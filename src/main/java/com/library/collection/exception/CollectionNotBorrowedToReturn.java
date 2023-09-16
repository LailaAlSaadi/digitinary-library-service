package com.library.collection.exception;

public class CollectionNotBorrowedToReturn extends RuntimeException {

    public CollectionNotBorrowedToReturn() {
        super("Collection is not borrowed to be returned");
    }
}
