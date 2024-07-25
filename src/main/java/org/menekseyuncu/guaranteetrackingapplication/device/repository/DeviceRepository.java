package org.menekseyuncu.guaranteetrackingapplication.device.repository;

import org.menekseyuncu.guaranteetrackingapplication.device.model.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//todo: javadoc ekle
@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

    //todo: javadoc ekle
    Optional<DeviceEntity> findByBrandAndModel(String brand, String model);
}
