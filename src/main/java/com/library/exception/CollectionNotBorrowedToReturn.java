package com.library.exception;

public class CollectionNotBorrowedToReturn extends BusinessException {

    public CollectionNotBorrowedToReturn() {
        super("Collection is not borrowed to be returned");
    }
}
