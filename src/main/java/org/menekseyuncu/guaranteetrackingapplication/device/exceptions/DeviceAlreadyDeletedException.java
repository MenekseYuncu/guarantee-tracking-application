package org.menekseyuncu.guaranteetrackingapplication.device.exceptions;

import org.menekseyuncu.guaranteetrackingapplication.common.exception.AlreadyChangedException;

import java.io.Serial;

/**
 * Exception thrown when an attempt is made to modify or delete a device that has already been marked as deleted.
 */
public class DeviceAlreadyDeletedException extends AlreadyChangedException {

    @Serial
    private static final long serialVersionUID = -3083522461234735405L;

    /**
     * Constructs a new DeviceAlreadyDeletedException with a default message.
     */
    public DeviceAlreadyDeletedException() {
        super("Device status already changed");
    }
}