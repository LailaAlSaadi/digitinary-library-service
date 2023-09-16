package com.library.collection.exception;

import com.library.collection.model.enums.CollectionStatus;

import java.util.Arrays;

public class InvalidCollectionStatus extends RuntimeException {
    public InvalidCollectionStatus() {
        super("Invalid status was provided use only " + Arrays.toString(CollectionStatus.values()));
    }

}
