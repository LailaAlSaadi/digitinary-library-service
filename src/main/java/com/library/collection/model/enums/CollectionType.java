package com.library.collection.model.enums;

import com.library.collection.exception.InvalidCollectionType;
import org.apache.commons.lang3.EnumUtils;

public enum CollectionType {

    BOOK, JOURNAL;

    public static void validate(String type) {
        if (!EnumUtils.isValidEnum(CollectionType.class, type)) {
            throw new InvalidCollectionType();
        }
    }
}
