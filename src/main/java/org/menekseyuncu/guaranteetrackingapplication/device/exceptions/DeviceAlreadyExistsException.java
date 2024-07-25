package org.menekseyuncu.guaranteetrackingapplication.device.exceptions;

import org.menekseyuncu.guaranteetrackingapplication.common.exception.AlreadyExistException;

import java.io.Serial;

public class DeviceAlreadyExistsException extends AlreadyExistException {


    @Serial
    private static final long serialVersionUID = -1218861788820603676L;

    public DeviceAlreadyExistsException() {
        super("Device already changed");
    }
}
