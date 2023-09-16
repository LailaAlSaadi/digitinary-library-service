package com.library.service;

import com.library.exception.CollectionAlreadyBorrowedException;
import com.library.exception.CollectionNotBorrowedToReturn;
import com.library.exception.CollectionNotFoundException;
import com.library.exception.DuplicateCollectionException;
import com.library.model.command.AddCollectionRequest;
import com.library.model.command.UpdateCollectionRequest;
import com.library.model.entity.Collection;
import com.library.model.enums.CollectionStatus;
import com.library.model.enums.CollectionType;
import com.library.repository.CollectionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private final CollectionJpaRepository collectionJpaRepository;

    public CollectionServiceImpl(CollectionJpaRepository collectionJpaRepository) {
        this.collectionJpaRepository = collectionJpaRepository;
    }

    @Override
    public String add(AddCollectionRequest request) {
        CollectionType.validate(request.getType());

        if (collectionJpaRepository.findCollectionByTitle(request.getTitle().toUpperCase()).isPresent()) {
            throw new DuplicateCollectionException();
        }
        Collection map = Collection.builder()
                .collectionId(UUID.randomUUID().toString())
                .createdDate(LocalDateTime.now())
                .title(request.getTitle().toUpperCase())
                .type(CollectionType.valueOf(request.getType()))
                .status(CollectionStatus.AVAILABLE)
                .build();
        return collectionJpaRepository.save(map).getCollectionId();
    }

    @Override
    public void delete(String id) {
        Collection collection = collectionJpaRepository.findByCollectionId(id)
                .orElseThrow(CollectionNotFoundException::new);
        collectionJpaRepository.delete(collection);
    }

    @Override
    public void update(UpdateCollectionRequest collectionRequest) {
        Collection collection = collectionJpaRepository.findByCollectionId(collectionRequest.getCollectionId())
                .orElseThrow(CollectionNotFoundException::new);

        if (collectionRequest.getTitle() != null && !collectionRequest.getTitle().isEmpty()) {
            collection.setTitle(collectionRequest.getTitle());
        }
        if (collectionRequest.getType() != null) {
            collection.setType(CollectionType.valueOf(collectionRequest.getType()));
        }
        if (collectionRequest.getStatus() != null) {
            CollectionStatus.validate(collectionRequest.getStatus());
            collection.setStatus(CollectionStatus.valueOf(collectionRequest.getStatus()));
        }
        collection.setLastModifiedDate(LocalDateTime.now());
        collectionJpaRepository.save(collection);
    }

    @Override
    public void borrowCollection(String collectionId) {
        Collection collection = collectionJpaRepository.findByCollectionId(collectionId)
                .orElseThrow(CollectionNotFoundException::new);

        if (collection.getStatus().equals(CollectionStatus.NOT_AVAILABLE)) {
            throw new CollectionAlreadyBorrowedException();
        }

        collection.setStatus(CollectionStatus.NOT_AVAILABLE);
        collection.setLastModifiedDate(LocalDateTime.now());
        collectionJpaRepository.save(collection);
    }

    @Override
    public void returnCollection(String collectionId) {
        Collection collection = collectionJpaRepository.findByCollectionId(collectionId)
                .orElseThrow(CollectionNotFoundException::new);

        if (collection.getStatus().equals(CollectionStatus.AVAILABLE)) {
            throw new CollectionNotBorrowedToReturn();
        }

        collection.setStatus(CollectionStatus.AVAILABLE);
        collection.setLastModifiedDate(LocalDateTime.now());
        collectionJpaRepository.save(collection);
    }

}
