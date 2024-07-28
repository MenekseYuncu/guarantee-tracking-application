package org.menekseyuncu.guaranteetrackingapplication.warranty.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.menekseyuncu.guaranteetrackingapplication.device.model.entity.DeviceEntity;
import org.menekseyuncu.guaranteetrackingapplication.warranty.model.enums.WarrantyStatus;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Warranty {

    private Long deviceId;
    private DeviceEntity device;
    private LocalDate purchaseDate;
    private WarrantyStatus status;

}
