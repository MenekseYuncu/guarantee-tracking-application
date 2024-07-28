package org.menekseyuncu.guaranteetrackingapplication.device.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * A record representing the request payload for updating a device's information.
 *
 * @param brand the brand of the device, must not be blank and must be between 2 and 300 characters long
 * @param model the model of the device, must not be blank and must be between 2 and 500 characters long
 */
public record DeviceUpdateRequest(

        @NotBlank
        @Size(min = 2, max = 300)
        String brand,

        @NotBlank
        @Size(min = 2, max = 500)
        String model

) {

}
