package com.library.collection.controller;

import com.library.collection.model.entity.Collection;
import com.library.collection.model.enums.CollectionType;
import com.library.collection.service.CollectionQueryManagerImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Search Collection Service",
        description = "Search Collection Service"
)
@Validated
@RestController
@RequestMapping(value = "/collections")
public class SearchCollectionController {

    private final CollectionQueryManagerImpl collectionQueryManager;

    @Autowired
    public SearchCollectionController(CollectionQueryManagerImpl collectionQueryManager) {
        this.collectionQueryManager = collectionQueryManager;
    }

    @Operation(summary = "get collection[book/journal] by collection id")
    @GetMapping(path = "/{collectionId}", produces = "application/json")
    public ResponseEntity<Collection> get(@PathVariable String collectionId) {
        Collection collection = collectionQueryManager.getByCollectionId(collectionId);
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @Operation(summary = "get available collections by type book or journal, if not provided both will be returned")
    @GetMapping(path = "/available", produces = "application/json")
    public ResponseEntity<List<Collection>> getAvailable(@RequestParam(required = false) String type) {
        return new ResponseEntity<>(collectionQueryManager.getAvailableCollections(type), HttpStatus.OK);
    }

    @Operation(summary = "get all collections by type book or journal, if not provided both will be returned")
    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<List<Collection>> getAllOfType(@RequestParam(required = false) String type) {
        return new ResponseEntity<>(collectionQueryManager.getAllCollectionsByType(type), HttpStatus.OK);
    }

}

