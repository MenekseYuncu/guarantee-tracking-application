package org.menekseyuncu.guaranteetrackingapplication.device.repository;

import org.menekseyuncu.guaranteetrackingapplication.device.model.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {
}
