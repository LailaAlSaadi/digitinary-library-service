package com.library.collection.model.enums;

import com.library.collection.exception.InvalidCollectionStatus;
import org.apache.commons.lang3.EnumUtils;

public enum CollectionStatus {

    AVAILABLE, NOT_AVAILABLE;

    public static void validate(String type) {
        if (!EnumUtils.isValidEnum(CollectionStatus.class, type)) {
            throw new InvalidCollectionStatus();
        }
    }
}
