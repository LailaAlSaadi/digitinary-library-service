package com.library.collection.model.entity;

import com.library.collection.model.enums.CollectionType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CollectionTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void givenNullTitle_whenCtrateCollection_thenValidationErrorAdded() {
        Collection collection = Collection.builder().type(CollectionType.BOOK).build();
        Set<ConstraintViolation<Collection>> violations = validator.validate(collection);
        assertFalse(violations.isEmpty());
        ConstraintViolation<Collection> violation = violations.stream().toList().get(0);
        assertEquals("title", violation.getPropertyPath().toString());
        assertEquals("title must be provided", violation.getMessage());
    }

    @Test
    public void givenNullType_whenCtrateCollection_thenValidationErrorAdded() {
        Collection collection = Collection.builder().title("TITLE 1").build();
        Set<ConstraintViolation<Collection>> violations = validator.validate(collection);
        assertFalse(violations.isEmpty());
        ConstraintViolation<Collection> violation = violations.stream().toList().get(0);
        assertEquals("type", violation.getPropertyPath().toString());
        assertEquals("type must be provided", violation.getMessage());
    }
}
