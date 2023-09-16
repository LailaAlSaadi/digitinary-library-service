package com.library.collection.service;

import com.library.collection.exception.CollectionAlreadyBorrowedException;
import com.library.collection.exception.CollectionNotBorrowedToReturn;
import com.library.collection.exception.CollectionNotFoundException;
import com.library.collection.exception.DuplicateCollectionException;
import com.library.collection.model.command.AddCollectionRequest;
import com.library.collection.model.command.UpdateCollectionRequest;
import com.library.collection.model.entity.Collection;
import com.library.collection.model.enums.CollectionType;
import com.library.collection.repository.CollectionJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.library.collection.model.enums.CollectionStatus.AVAILABLE;
import static com.library.collection.model.enums.CollectionStatus.NOT_AVAILABLE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CollectionServiceImplTest {

    private final CollectionService collectionServiceImpl;

    @Mock
    private CollectionJpaRepository collectionJpaRepository;

    CollectionServiceImplTest() {
        MockitoAnnotations.initMocks(this);

        Collection availableCollection = Collection.builder().collectionId("1").type(CollectionType.JOURNAL).title("TITLE TEST 1").status(AVAILABLE).build();
        Collection unavailableCollection = Collection.builder().collectionId("2").type(CollectionType.BOOK).title("TITLE TEST 2").status(NOT_AVAILABLE).build();

        when(collectionJpaRepository.findByCollectionId("1")).thenReturn(Optional.of(availableCollection));
        when(collectionJpaRepository.findByCollectionId("2")).thenReturn(Optional.of(unavailableCollection));
        when(collectionJpaRepository.findCollectionByTitle("TITLE TEST 2")).thenReturn(Optional.of(availableCollection));
        when(collectionJpaRepository.save(any())).thenReturn(availableCollection);

        this.collectionServiceImpl = new CollectionServiceImpl(collectionJpaRepository);
    }

    @Test
    void givenValidCollection_whenAddCollection_thenSuccess() {
        AddCollectionRequest addRequest = new AddCollectionRequest();
        addRequest.setTitle("TITLE TEST");
        addRequest.setType("BOOK");

        String id = collectionServiceImpl.add(addRequest);
        assertNotNull(id);
    }

    @Test
    void givenExistingCollectionTitleRequest_whenAddCollection_thenException() {
        AddCollectionRequest addRequest = new AddCollectionRequest();
        addRequest.setCollectionId("2");
        addRequest.setTitle("TITLE TEST 2");
        assertThrows(DuplicateCollectionException.class, () ->
                collectionServiceImpl.add(addRequest));
    }

    @Test
    void givenValidCollectionRequest_whenUpdateCollection_thenSuccess() {
        UpdateCollectionRequest updateRequest = new UpdateCollectionRequest();
        updateRequest.setCollectionId("1");
        updateRequest.setType("BOOK");
        updateRequest.setTitle("TITLE TEST 3");
        collectionServiceImpl.update(updateRequest);
    }


    @Test
    void givenUnExistingCollection_whenDeleteCollection_thenException() {
        assertThrows(CollectionNotFoundException.class, () ->
                collectionServiceImpl.delete("3"));
    }

    @Test
    void givenExistingCollection_whenDeleteCollection_thenSuccess() {
        collectionServiceImpl.delete("1");
    }

    @Test
    void givenUnavailableCollection_whenBorrow_thenException() {
        assertThrows(CollectionAlreadyBorrowedException.class, () ->
                collectionServiceImpl.borrowCollection("2"));
    }

    @Test
    void givenAvailableCollection_whenBorrow_thenSuccess() {
        collectionServiceImpl.borrowCollection("1");
    }

    @Test
    void givenUnavailableCollection_whenReturn_thenException() {
        assertThrows(CollectionNotBorrowedToReturn.class, () ->
                collectionServiceImpl.returnCollection("1"));
    }

    @Test
    void givenAvailableCollection_whenReturn_thenSuccess() {
        collectionServiceImpl.returnCollection("2");
    }
}