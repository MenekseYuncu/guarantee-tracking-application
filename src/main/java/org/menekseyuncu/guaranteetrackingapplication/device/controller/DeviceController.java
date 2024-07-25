package org.menekseyuncu.guaranteetrackingapplication.device.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.menekseyuncu.guaranteetrackingapplication.common.controller.response.BaseResponse;
import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceCreateRequest;
import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceUpdateRequest;
import org.menekseyuncu.guaranteetrackingapplication.device.service.DeviceService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing devices.
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;


    /**
     * Creates a new device.
     *
     * @param request the request containing the device details
     * @return a BaseResponse indicating success
     */
    @PostMapping
    public BaseResponse<Void> create(
            @Valid @RequestBody DeviceCreateRequest request
    ) {
        deviceService.createDevice(request);
        return BaseResponse.SUCCESS;
    }


    /**
     * Updates an existing device.
     *
     * @param id      the ID of the device to update
     * @param request the request containing the updated device details
     * @return a BaseResponse indicating success
     */
    @PutMapping("/{id}")
    public BaseResponse<Void> update(
            @PathVariable @Positive Long id,
            @Valid @RequestBody DeviceUpdateRequest request
    ) {
        deviceService.updateDevice(id, request);
        return BaseResponse.SUCCESS;
    }


    /**
     * Deletes an existing device.
     *
     * @param id the ID of the device to delete
     * @return a BaseResponse indicating success
     */
    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(
            @PathVariable @Positive Long id
    ) {
        deviceService.deleteDevice(id);
        return BaseResponse.SUCCESS;
    }

}
