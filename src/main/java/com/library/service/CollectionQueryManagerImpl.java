package com.library.service;

import com.library.exception.CollectionNotFoundException;
import com.library.model.entity.Collection;
import com.library.model.enums.CollectionStatus;
import com.library.model.enums.CollectionType;
import com.library.repository.CollectionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionQueryManagerImpl implements CollectionQueryManager {

    @Autowired
    private final CollectionJpaRepository collectionRepository;

    @Autowired
    public CollectionQueryManagerImpl(CollectionJpaRepository collectionJpaRepository) {
        this.collectionRepository = collectionJpaRepository;
    }

    public Collection getByCollectionId(String collectionId) {
        return collectionRepository.findByCollectionId(collectionId).orElseThrow(CollectionNotFoundException::new);

    }

    @Override
    public List<Collection> getAllCollectionsByType(String type) {
        if (type != null) {
            CollectionType.validate(type);
            return collectionRepository.findAllByType(CollectionType.valueOf(type));
        }
        return collectionRepository.findAll();
    }

    public List<Collection> getAvailableCollections(String type) {
        if (type != null) {
            CollectionType.validate(type);
            return collectionRepository.findAvailableByType(CollectionStatus.AVAILABLE, CollectionType.valueOf(type));
        }
        return collectionRepository.findByStatusType(CollectionStatus.AVAILABLE);
    }

}
