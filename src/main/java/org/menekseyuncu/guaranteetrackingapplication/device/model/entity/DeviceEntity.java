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

// TODO: javadoc ekle
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "device")
public class DeviceEntity extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    //todo: javadoc ekle
    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}
