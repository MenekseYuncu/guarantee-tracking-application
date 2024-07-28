package org.menekseyuncu.guaranteetrackingapplication.device.exceptions;

import org.menekseyuncu.guaranteetrackingapplication.common.exception.NotFoundException;

import java.io.Serial;

/**
 * Exception thrown when a device is not found in the system.
 */
public class DeviceNotFoundException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = 4786518961610409407L;

    /**
     * Constructs a new DeviceNotFoundException with a default message.
     */
    public DeviceNotFoundException() {
        super("Device not found");
    }
}