package org.menekseyuncu.guaranteetrackingapplication.device.service;

import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceCreateRequest;

public interface DeviceService {

    void createDevice(DeviceCreateRequest createRequest);

}
