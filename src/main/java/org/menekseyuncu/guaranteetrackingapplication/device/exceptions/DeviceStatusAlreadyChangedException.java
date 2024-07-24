package org.menekseyuncu.guaranteetrackingapplication.device.exceptions;

import org.menekseyuncu.guaranteetrackingapplication.common.exception.StatusAlreadyChangedException;

import java.io.Serial;

public class DeviceStatusAlreadyChangedException extends StatusAlreadyChangedException {


    @Serial
    private static final long serialVersionUID = -3083522461234735405L;

    public DeviceStatusAlreadyChangedException() {
        super("Device status already changed");
    }
}
