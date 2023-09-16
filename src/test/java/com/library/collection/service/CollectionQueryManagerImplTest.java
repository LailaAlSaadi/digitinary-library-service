package com.library.collection.service;

import com.library.collection.exception.CollectionNotFoundException;
import com.library.collection.model.entity.Collection;
import com.library.collection.repository.CollectionJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.library.collection.model.enums.CollectionStatus.AVAILABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CollectionQueryManagerImplTest {

    private final CollectionQueryManager collectionQueryManagerImpl;

    @Mock
    private CollectionJpaRepository collectionJpaRepository;

    public CollectionQueryManagerImplTest() {
        MockitoAnnotations.initMocks(this);
        Collection collection = new Collection();
        collection.setId(1);
        collection.setCollectionId("1");
        collection.setTitle("TITLE TEST");
        collection.setCreatedDate(LocalDateTime.now());
        collection.setStatus(AVAILABLE);
        when(collectionJpaRepository.findByCollectionId("1")).thenReturn(Optional.of(collection));
        collectionQueryManagerImpl = new CollectionQueryManagerImpl(collectionJpaRepository);
    }

    @Test
    void givenExistingCollectionId_whenGetCollectionId_thenSuccess() {
        Collection collection = collectionQueryManagerImpl.getByCollectionId("1");
        assertEquals(1, collection.getId());
        assertEquals("1", collection.getCollectionId());
        assertEquals("TITLE TEST", collection.getTitle());
        assertEquals(AVAILABLE, collection.getStatus());
    }

    @Test
    void givenUnExistingCollectionId_whenGetCollectionById_thenException() {
        assertThrows(CollectionNotFoundException.class, () ->
                collectionQueryManagerImpl.getByCollectionId("2"));
    }


    @Test
    void returnAllCollections_whenGetAllCollection_thenSuccess() {
        collectionQueryManagerImpl.getAllCollectionsByType(null);
    }

    @Test
    void returnAllCollections_whenGetAllCollectionByType_thenSuccess() {
        collectionQueryManagerImpl.getAllCollectionsByType("BOOK");
    }


}