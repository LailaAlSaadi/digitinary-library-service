package com.library.collection.exception;

import com.library.collection.model.enums.CollectionType;

import java.util.Arrays;

public class InvalidCollectionType extends RuntimeException {
    public InvalidCollectionType() {
        super("Invalid type was provided use only " + Arrays.toString(CollectionType.values()));
    }
}
