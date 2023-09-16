package com.library.service;

import com.library.model.entity.Collection;

import java.util.List;

public interface CollectionQueryManager {

    Collection getByCollectionId(String collectionId);

    List<Collection> getAllCollectionsByType(String type);

    List<Collection> getAvailableCollections(String type);

}