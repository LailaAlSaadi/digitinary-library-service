package com.library.controller;

import com.library.service.CollectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Collection Borrow Service",
        description = "Collection Borrow Service"
)
@RestController
@RequestMapping(value = "/collections")
@Validated
public class CollectionBorrowController {

    private final CollectionService collectionService;

    @Autowired
    public CollectionBorrowController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @Operation(summary = "borrow collection[book/journal] by collectionId")
    @PutMapping(path = "/{collectionId}/borrow", produces = "application/json")
    public ResponseEntity<?> borrow(@PathVariable String collectionId) {
        collectionService.borrowCollection(collectionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "return collection[book/journal] by collectionId")
    @PutMapping(path = "/{collectionId}/return", produces = "application/json")
    public ResponseEntity<?> returnCollection(@PathVariable String collectionId) {
        collectionService.returnCollection(collectionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

