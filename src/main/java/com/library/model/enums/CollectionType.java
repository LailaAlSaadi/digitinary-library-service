package com.library.model.enums;

import com.library.exception.InvalidCollectionType;
import org.apache.commons.lang3.EnumUtils;

public enum CollectionType {

    BOOK, JOURNAL;

    public static void validate(String type) {
        if (!EnumUtils.isValidEnum(CollectionType.class, type)) {
            throw new InvalidCollectionType();
        }
    }
}
