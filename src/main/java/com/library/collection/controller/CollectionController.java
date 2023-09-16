package com.library.collection.controller;

import com.library.collection.model.command.AddCollectionRequest;
import com.library.collection.model.command.UpdateCollectionRequest;
import com.library.collection.model.response.CollectionIdResponse;
import com.library.collection.service.CollectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Tag(
        name = "Collection Management Service",
        description = "Collection Management Service"
)
@RestController
@RequestMapping(value = "/collections")
@Validated
public class CollectionController {

    private final CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @Operation(summary = "add new collection[book/journal]")
    @PostMapping(path = "/", produces = "application/json")
    public ResponseEntity<CollectionIdResponse> add(@Valid @RequestBody AddCollectionRequest collection) {
        String id = collectionService.add(collection);
        return new ResponseEntity<>(new CollectionIdResponse(id), HttpStatus.OK);
    }

    @Operation(summary = "delete collection[book/journal] by collectionId")
    @DeleteMapping(path = "/{collectionId}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable String collectionId) {
        collectionService.delete(collectionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "update collection[book/journal] by collectionId")
    @PutMapping(path = "/{collectionId}", produces = "application/json")
    public ResponseEntity<?> update(@PathVariable String collectionId, @Valid @RequestBody UpdateCollectionRequest request) {
        request.setCollectionId(collectionId);
        collectionService.update(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

