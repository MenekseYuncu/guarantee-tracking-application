package org.menekseyuncu.guaranteetrackingapplication.device.service.impl;

import lombok.RequiredArgsConstructor;
import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceCreateRequest;
import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceUpdateRequest;
import org.menekseyuncu.guaranteetrackingapplication.device.exceptions.DeviceAlreadyDeletedException;
import org.menekseyuncu.guaranteetrackingapplication.device.exceptions.DeviceAlreadyExistsException;
import org.menekseyuncu.guaranteetrackingapplication.device.exceptions.DeviceAlreadyUpdatedException;
import org.menekseyuncu.guaranteetrackingapplication.device.exceptions.DeviceNotFoundException;
import org.menekseyuncu.guaranteetrackingapplication.device.model.entity.DeviceEntity;
import org.menekseyuncu.guaranteetrackingapplication.device.model.domain.Device;
import org.menekseyuncu.guaranteetrackingapplication.device.repository.DeviceRepository;
import org.menekseyuncu.guaranteetrackingapplication.device.service.DeviceService;
import org.menekseyuncu.guaranteetrackingapplication.device.service.mapper.DeviceCreateRequestToDeviceMapper;
import org.menekseyuncu.guaranteetrackingapplication.device.service.mapper.DeviceToDeviceEntityMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Service implementation for managing devices.
 */
@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {


    private final DeviceRepository deviceRepository;

    private static final DeviceCreateRequestToDeviceMapper createRequestToDeviceMapper = DeviceCreateRequestToDeviceMapper.INSTANCE;
    private static final DeviceToDeviceEntityMapper deviceToEntityMapper = DeviceToDeviceEntityMapper.INSTANCE;


    //todo: javadoc ekle
    @Override
    public void createDevice(DeviceCreateRequest createRequest) {
        Device device = createRequestToDeviceMapper.map(createRequest);

        device.setSerialNumber(this.generateUniqueSerialNumber());

        deviceRepository.save(deviceToEntityMapper.map(device));
    }


    //todo: javadoc ekle
    @Override
    public void updateDevice(Long id, DeviceUpdateRequest updateRequest) {
        DeviceEntity deviceEntity = deviceRepository.findById(id)
                .orElseThrow(DeviceNotFoundException::new);

        this.checkExistingOfBrandAndModelIfChanged(updateRequest, deviceEntity);

        deviceEntity.setBrand(updateRequest.brand());
        deviceEntity.setModel(updateRequest.model());
        deviceEntity.setSerialNumber(this.generateUniqueSerialNumber());

        deviceRepository.save(deviceEntity);
    }


    //todo: javadoc ekle
    @Override
    public void deleteDevice(Long id) {
        DeviceEntity deviceEntity = deviceRepository.findById(id)
                .orElseThrow(DeviceNotFoundException::new);

        this.checkExistingStatus(deviceEntity.getDeletedAt());

        deviceEntity.delete();

        deviceRepository.save(deviceEntity);
    }


    //todo: javadoc ekle
    private String generateUniqueSerialNumber() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 11);
    }

    //todo: javadoc ekle
    private void checkExistingStatus(LocalDateTime deletedAt) {
        if (deletedAt != null) {
            throw new DeviceAlreadyDeletedException();
        }
    }

    //todo: javadoc ekle
    private void checkExistingOfBrandAndModelIfChanged(DeviceUpdateRequest updateRequest, DeviceEntity deviceEntity) {
        boolean brandChanged = !deviceEntity.getBrand().equalsIgnoreCase(updateRequest.brand());
        boolean modelChanged = !deviceEntity.getModel().equalsIgnoreCase(updateRequest.model());

        if (!brandChanged && !modelChanged) {
            throw new DeviceAlreadyUpdatedException();
        }

        Optional<DeviceEntity> existingDevice = deviceRepository.findByBrandAndModel(updateRequest.brand(), updateRequest.model());
        if (existingDevice.isPresent()) {
            throw new DeviceAlreadyExistsException();
        }
    }
}
