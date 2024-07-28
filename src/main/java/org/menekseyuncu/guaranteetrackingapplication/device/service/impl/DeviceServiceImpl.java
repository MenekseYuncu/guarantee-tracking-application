package org.menekseyuncu.guaranteetrackingapplication.device.service.impl;

import lombok.RequiredArgsConstructor;
import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceCreateRequest;
import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceUpdateRequest;
import org.menekseyuncu.guaranteetrackingapplication.device.exceptions.DeviceAlreadyDeletedException;
import org.menekseyuncu.guaranteetrackingapplication.device.exceptions.DeviceAlreadyExistsException;
import org.menekseyuncu.guaranteetrackingapplication.device.exceptions.DeviceAlreadyUpdatedException;
import org.menekseyuncu.guaranteetrackingapplication.device.exceptions.DeviceNotFoundException;
import org.menekseyuncu.guaranteetrackingapplication.device.model.domain.Device;
import org.menekseyuncu.guaranteetrackingapplication.device.model.entity.DeviceEntity;
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


    /**
     * Creates a new device based on the provided {@link DeviceCreateRequest}.
     *
     * @param createRequest the request object containing device details
     */
    @Override
    public void createDevice(DeviceCreateRequest createRequest) {
        Device device = createRequestToDeviceMapper.map(createRequest);

        device.setSerialNumber(this.generateUniqueSerialNumber());

        deviceRepository.save(deviceToEntityMapper.map(device));
    }


    /**
     * Updates an existing device with the given ID based on the provided {@link DeviceUpdateRequest}.
     *
     * @param id            the ID of the device to update
     * @param updateRequest the request object containing updated device details
     * @throws DeviceNotFoundException       if the device with the given ID is not found
     * @throws DeviceAlreadyUpdatedException if the device brand and model have not changed
     * @throws DeviceAlreadyExistsException  if a device with the same brand and model already exists
     */
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


    /**
     * Deletes the device with the given ID.
     *
     * @param id the ID of the device to delete
     * @throws DeviceNotFoundException       if the device with the given ID is not found
     * @throws DeviceAlreadyDeletedException if the device has already been deleted
     */
    @Override
    public void deleteDevice(Long id) {
        DeviceEntity deviceEntity = deviceRepository.findById(id)
                .orElseThrow(DeviceNotFoundException::new);

        this.checkExistingDevice(deviceEntity.getDeletedAt());

        deviceEntity.delete();

        deviceRepository.save(deviceEntity);
    }


    /**
     * Generates a unique serial number for a device.
     *
     * @return the generated unique serial number
     */
    private String generateUniqueSerialNumber() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 11);
    }

    /**
     * Checks if the device has already been deleted.
     *
     * @param deletedAt the timestamp when the device was deleted
     * @throws DeviceAlreadyDeletedException if the device has already been deleted
     */
    private void checkExistingDevice(LocalDateTime deletedAt) {
        if (deletedAt != null) {
            throw new DeviceAlreadyDeletedException();
        }
    }

    /**
     * Checks if the brand and model of the device have changed and if a device with the same brand and model already exists.
     *
     * @param updateRequest the request object containing updated device details
     * @param deviceEntity  the existing device entity
     * @throws DeviceAlreadyUpdatedException if the device brand and model have not changed
     * @throws DeviceAlreadyExistsException  if a device with the same brand and model already exists
     */
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
