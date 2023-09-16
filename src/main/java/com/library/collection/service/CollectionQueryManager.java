package com.library.collection.service;

import com.library.collection.model.entity.Collection;
import com.library.collection.model.enums.CollectionType;

import java.util.List;

public interface CollectionQueryManager {

    Collection getByCollectionId(String collectionId);

    List<Collection> getAllCollectionsByType(String type);

    List<Collection> getAvailableCollections(String type);

}