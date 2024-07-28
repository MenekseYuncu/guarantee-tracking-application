package org.menekseyuncu.guaranteetrackingapplication.warranty.service;

import org.menekseyuncu.guaranteetrackingapplication.warranty.model.domain.Warranty;

import java.util.List;

/**
 * Service interface for managing warranty-related operations.
 * Provides methods to create warranties, retrieve expired warranties, and get the warranty status for specific devices.
 */
public interface WarrantyService {

    /**
     * Creates a new warranty for a device with the specified ID.
     * Initializes the warranty with the current date as the purchase date and sets its status to ACTIVE.
     *
     * @param deviceId the ID of the device for which to create a warranty
     */
    void createWarrantyForDevice(Long deviceId);

    /**
     * Retrieves a list of warranties that have expired.
     *
     * @return a list of {@link Warranty} objects representing expired warranties
     */
    List<Warranty> getExpiredWarranties();

    /**
     * Retrieves the warranty status for a specific device based on its ID.
     *
     * @param deviceId the ID of the device whose warranty status is to be retrieved
     * @return a {@link Warranty} object containing the warranty information for the device
     */
    Warranty getWarrantyStatus(Long deviceId);
}