package org.menekseyuncu.guaranteetrackingapplication.device.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DeviceUpdateRequest(

        @NotBlank
        @Size(min = 2, max = 300)
        String brand,

        @NotBlank
        @Size(min = 2, max = 500)
        String model

) {

}
