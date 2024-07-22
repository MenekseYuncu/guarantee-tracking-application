package org.menekseyuncu.guaranteetrackingapplication.device.model.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Device {

    private Long id;
    private String serialNumber;
    private String brand;
    private String model;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
