package org.menekseyuncu.guaranteetrackingapplication.device.repository;

import org.menekseyuncu.guaranteetrackingapplication.device.model.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link DeviceEntity} entities.
 */
@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

    /**
     * Finds a {@link DeviceEntity} by its brand and model.
     *
     * @param brand the brand of the device
     * @param model the model of the device
     * @return an {@link Optional} containing the {@link DeviceEntity} if found, or {@link Optional#empty()} if not found
     */
    Optional<DeviceEntity> findByBrandAndModel(String brand, String model);
}