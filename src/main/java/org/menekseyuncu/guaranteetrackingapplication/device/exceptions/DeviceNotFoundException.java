package org.menekseyuncu.guaranteetrackingapplication.device.exceptions;

import org.menekseyuncu.guaranteetrackingapplication.common.exception.NotFoundException;

import java.io.Serial;

public class DeviceNotFoundException extends NotFoundException {


    @Serial
    private static final long serialVersionUID = 4786518961610409407L;


    public DeviceNotFoundException() {
        super("Device not found");
    }
}
