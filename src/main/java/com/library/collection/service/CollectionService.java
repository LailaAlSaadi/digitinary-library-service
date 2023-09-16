package com.library.collection.service;


import com.library.collection.model.command.AddCollectionRequest;
import com.library.collection.model.command.UpdateCollectionRequest;

public interface CollectionService {

    String add(AddCollectionRequest request);

    void delete(String id);

    void update(UpdateCollectionRequest request);

    void borrowCollection(String collectionId);

    void returnCollection(String collectionId);
}
