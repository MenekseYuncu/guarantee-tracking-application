package org.menekseyuncu.guaranteetrackingapplication.device.exceptions;

import org.menekseyuncu.guaranteetrackingapplication.common.exception.AlreadyExistException;

import java.io.Serial;

/**
 * Exception thrown when an attempt is made to update a device that has already been updated.
 */
public class DeviceAlreadyUpdatedException extends AlreadyExistException {

    @Serial
    private static final long serialVersionUID = 6674179126864160368L;

    /**
     * Constructs a new DeviceAlreadyUpdatedException with a default message.
     */
    public DeviceAlreadyUpdatedException() {
        super("Device already updated!");
    }
}