package org.menekseyuncu.guaranteetrackingapplication.warranty.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.menekseyuncu.guaranteetrackingapplication.common.repository.entity.BaseEntity;
import org.menekseyuncu.guaranteetrackingapplication.device.model.entity.DeviceEntity;
import org.menekseyuncu.guaranteetrackingapplication.warranty.model.enums.WarrantyStatus;

import java.time.LocalDate;

/**
 * Entity representing a warranty in the system.
 */
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "warranty")
public class WarrantyEntity extends BaseEntity {

    /**
     * The unique identifier for the warranty.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The ID of the device associated with the warranty.
     */
    @Column(name = "device_id")
    private Long deviceId;

    /**
     * The device associated with the warranty.
     * This field is mapped to the 'device_id' column, but it is not insertable or updatable directly.
     */
    @OneToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id", insertable = false, updatable = false)
    private DeviceEntity device;

    /**
     * The date when the warranty was purchased.
     */
    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    /**
     * The current status of the warranty.
     */
    @Column(name = "warranty_status")
    @Enumerated(EnumType.STRING)
    private WarrantyStatus status;
}