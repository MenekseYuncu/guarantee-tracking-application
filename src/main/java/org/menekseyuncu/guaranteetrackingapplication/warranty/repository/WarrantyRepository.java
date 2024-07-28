package org.menekseyuncu.guaranteetrackingapplication.warranty.repository;


import org.menekseyuncu.guaranteetrackingapplication.warranty.model.entity.WarrantyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for accessing and managing {@link WarrantyEntity} entities.
 * Extends {@link JpaRepository} to provide basic CRUD operations and custom query methods.
 */
@Repository
public interface WarrantyRepository extends JpaRepository<WarrantyEntity, Long> {

    /**
     * Finds a {@link WarrantyEntity} by its associated device ID.
     *
     * @param deviceId the ID of the device for which to find the warranty
     * @return an {@link Optional} containing the {@link WarrantyEntity} if found, or an empty {@link Optional} if no warranty is associated with the specified device ID
     */
    Optional<WarrantyEntity> findByDeviceId(Long deviceId);

}
