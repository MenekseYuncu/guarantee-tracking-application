package org.menekseyuncu.guaranteetrackingapplication.device.exceptions;

import org.menekseyuncu.guaranteetrackingapplication.common.exception.AlreadyChangedException;

import java.io.Serial;

public class DeviceAlreadyDeletedException extends AlreadyChangedException {


    @Serial
    private static final long serialVersionUID = -3083522461234735405L;

    public DeviceAlreadyDeletedException() {
        super("Device status already changed");
    }
}
