package org.menekseyuncu.guaranteetrackingapplication.device.service.impl;

import lombok.RequiredArgsConstructor;
import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceCreateRequest;
import org.menekseyuncu.guaranteetrackingapplication.device.model.domain.Device;
import org.menekseyuncu.guaranteetrackingapplication.device.repository.DeviceRepository;
import org.menekseyuncu.guaranteetrackingapplication.device.service.DeviceService;
import org.menekseyuncu.guaranteetrackingapplication.device.service.mapper.DeviceCreateRequestToDeviceMapper;
import org.menekseyuncu.guaranteetrackingapplication.device.service.mapper.DeviceToDeviceEntityMapper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {


    private final DeviceRepository deviceRepository;

    private static final DeviceCreateRequestToDeviceMapper createRequestToDeviceMapper = DeviceCreateRequestToDeviceMapper.INSTANCE;
    private static final DeviceToDeviceEntityMapper deviceToEntityMapper = DeviceToDeviceEntityMapper.INSTANCE;

    private static final Random random = new Random();


    @Override
    public void createDevice(DeviceCreateRequest createRequest) {
        Device device = createRequestToDeviceMapper.map(createRequest);

        device.setSerialNumber(this.generateUniqueSerialNumber(createRequest.brand(), createRequest.model()));

        deviceRepository.save(deviceToEntityMapper.map(device));
    }

    private String generateUniqueSerialNumber(String brand, String model) {
        int randomNumber = random.nextInt(1000000);
        return brand + model + randomNumber;
    }
}
