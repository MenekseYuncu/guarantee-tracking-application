package org.menekseyuncu.guaranteetrackingapplication.device.exceptions;

import org.menekseyuncu.guaranteetrackingapplication.common.exception.AlreadyExistException;

import java.io.Serial;

/**
 * Exception thrown when an attempt is made to create or register a device that already exists.
 */
public class DeviceAlreadyExistsException extends AlreadyExistException {

    @Serial
    private static final long serialVersionUID = -1218861788820603676L;

    /**
     * Constructs a new DeviceAlreadyExistsException with a default message.
     */
    public DeviceAlreadyExistsException() {
        super("Device already changed");
    }
}