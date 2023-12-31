package com.library.exception;

import com.library.model.enums.CollectionStatus;

import java.util.Arrays;

public class InvalidCollectionStatus extends BusinessException {
    public InvalidCollectionStatus() {
        super("Invalid status was provided use only " + Arrays.toString(CollectionStatus.values()));
    }

}
