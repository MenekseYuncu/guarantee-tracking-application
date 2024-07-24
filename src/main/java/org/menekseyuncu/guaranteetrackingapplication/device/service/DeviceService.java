package org.menekseyuncu.guaranteetrackingapplication.device.service;

import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceCreateRequest;
import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceUpdateRequest;

/**
 * Service interface for managing devices.
 */
public interface DeviceService {

    /**
     * Creates a new device.
     *
     * @param createRequest the request containing the device details
     */
    void createDevice(DeviceCreateRequest createRequest);

    /**
     * Updates an existing device.
     *
     * @param id            the ID of the device to update
     * @param updateRequest the request containing updated device details
     */
    void updateDevice(Long id, DeviceUpdateRequest updateRequest);

    /**
     * Deletes an existing device.
     *
     * @param id the ID of the device to delete
     */
    void deleteDevice(Long id);
}
