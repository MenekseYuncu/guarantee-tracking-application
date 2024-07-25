package org.menekseyuncu.guaranteetrackingapplication.device.exceptions;

import org.menekseyuncu.guaranteetrackingapplication.common.exception.AlreadyExistException;

import java.io.Serial;

public class DeviceAlreadyUpdatedException extends AlreadyExistException {

    @Serial
    private static final long serialVersionUID = 6674179126864160368L;


    public DeviceAlreadyUpdatedException() {
        super("Device already updated!");
    }
}
