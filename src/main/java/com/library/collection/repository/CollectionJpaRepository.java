package com.library.collection.repository;

import com.library.collection.model.entity.Collection;
import com.library.collection.model.enums.CollectionStatus;
import com.library.collection.model.enums.CollectionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionJpaRepository extends JpaRepository<Collection, String> {

    @Query("SELECT c FROM Collection c WHERE c.collectionId = ?1")
    Optional<Collection> findByCollectionId(String collectionId);

    @Query("SELECT c FROM Collection c WHERE c.title = ?1 ")
    Optional<Collection> findCollectionByTitle(String title);

    @Query("SELECT c FROM Collection c WHERE c.status = ?1 and c.type= ?2")
    List<Collection> findAvailableByType(CollectionStatus status, CollectionType type);

    @Query("SELECT c FROM Collection c WHERE c.status = ?1")
    List<Collection> findByStatusType(CollectionStatus status);

    @Query("SELECT c FROM Collection c WHERE c.type = ?1")
    List<Collection> findAllByType(CollectionType type);
}
