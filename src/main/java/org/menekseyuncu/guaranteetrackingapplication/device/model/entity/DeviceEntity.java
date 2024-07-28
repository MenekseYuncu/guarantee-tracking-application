package org.menekseyuncu.guaranteetrackingapplication.device.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.menekseyuncu.guaranteetrackingapplication.common.repository.entity.BaseEntity;

import java.time.LocalDateTime;

/**
 * Represents a device entity in the guarantee tracking application.
 * This entity stores information about a device such as serial number, brand, and model.
 * It also extends {@link BaseEntity} to include common entity fields.
 */
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "device")
public class DeviceEntity extends BaseEntity {

    /**
     * The unique identifier for the device.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The serial number of the device.
     * This should be unique for each device.
     */
    @Column(name = "serial_number")
    private String serialNumber;

    /**
     * The brand of the device.
     */
    @Column(name = "brand")
    private String brand;

    /**
     * The model of the device.
     */
    @Column(name = "model")
    private String model;

    /**
     * The timestamp when the device was deleted.
     * If this field is null, the device is considered active.
     */
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    /**
     * Marks the device as deleted by setting the {@code deletedAt} field to the current timestamp.
     * This method should be used instead of actually removing the device from the database to keep a record of deleted devices.
     */
    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}
