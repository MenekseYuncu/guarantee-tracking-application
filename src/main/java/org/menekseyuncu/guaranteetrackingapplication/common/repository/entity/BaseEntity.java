package org.menekseyuncu.guaranteetrackingapplication.common.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/**
 * A base class for entities that require common timestamp fields.
 * This class is intended to be extended by other entity classes to include
 * created and updated timestamps. It is marked with {@link MappedSuperclass}
 * to indicate that it should not be used directly to create tables, but its
 * fields should be inherited by subclasses.
 */
@Getter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * The timestamp when the entity was created.
     * This field is automatically set to the current timestamp when the entity
     * is first persisted in the database.
     */
    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    /**
     * Sets the {@code createdAt} field to the current timestamp before
     * the entity is persisted.
     * This method is called automatically by the persistence provider.
     */
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    /**
     * The timestamp when the entity was last updated.
     * This field is automatically updated to the current timestamp before
     * the entity is updated in the database.
     */
    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;

    /**
     * Sets the {@code updatedAt} field to the current timestamp before
     * the entity is updated.
     * This method is called automatically by the persistence provider.
     */
    @PreUpdate
    public void preUpdate() {
        if (this.updatedAt == null) {
            this.updatedAt = LocalDateTime.now();
        }
    }
}