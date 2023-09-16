package com.library.collection.model.entity;

import com.library.collection.model.enums.CollectionStatus;
import com.library.collection.model.enums.CollectionType;
import com.library.core.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Collection")
@Table(name = "collections")
public class Collection extends BaseEntity {

    private String collectionId;

    private String title;

    private CollectionType type;

    private CollectionStatus status = CollectionStatus.AVAILABLE;

}
