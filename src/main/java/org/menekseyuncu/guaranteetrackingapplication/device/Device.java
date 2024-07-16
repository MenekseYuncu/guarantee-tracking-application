package org.menekseyuncu.guaranteetrackingapplication.device;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
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
