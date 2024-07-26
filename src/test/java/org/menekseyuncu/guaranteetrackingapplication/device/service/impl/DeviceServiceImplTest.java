package org.menekseyuncu.guaranteetrackingapplication.device.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.menekseyuncu.guaranteetrackingapplication.ServiceTest;
import org.menekseyuncu.guaranteetrackingapplication.TestContainer;
import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceCreateRequest;
import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceUpdateRequest;
import org.menekseyuncu.guaranteetrackingapplication.device.exceptions.DeviceAlreadyDeletedException;
import org.menekseyuncu.guaranteetrackingapplication.device.exceptions.DeviceAlreadyExistsException;
import org.menekseyuncu.guaranteetrackingapplication.device.exceptions.DeviceAlreadyUpdatedException;
import org.menekseyuncu.guaranteetrackingapplication.device.exceptions.DeviceNotFoundException;
import org.menekseyuncu.guaranteetrackingapplication.device.model.domain.Device;
import org.menekseyuncu.guaranteetrackingapplication.device.model.entity.DeviceEntity;
import org.menekseyuncu.guaranteetrackingapplication.device.repository.DeviceRepository;
import org.menekseyuncu.guaranteetrackingapplication.device.service.mapper.DeviceCreateRequestToDeviceMapper;
import org.menekseyuncu.guaranteetrackingapplication.device.service.mapper.DeviceToDeviceEntityMapper;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

class DeviceServiceImplTest extends ServiceTest implements TestContainer {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceServiceImpl deviceService;

    private static final DeviceCreateRequestToDeviceMapper createRequestToDeviceMapper = DeviceCreateRequestToDeviceMapper.INSTANCE;
    private static final DeviceToDeviceEntityMapper deviceToEntityMapper = DeviceToDeviceEntityMapper.INSTANCE;

    @Test
    void givenValidCreateDeviceRequest_whenCreateDeviceCorrect_thenSaveDeviceEntity() {
        // Gİven
        DeviceCreateRequest createRequest = new DeviceCreateRequest(
                "Brand",
                "Model"
        );

        // When
        Device device = createRequestToDeviceMapper.map(createRequest);
        DeviceEntity deviceEntity = deviceToEntityMapper.map(device);

        Mockito.when(deviceRepository.save(ArgumentMatchers.any(DeviceEntity.class)))
                .thenReturn(deviceEntity);

        deviceService.createDevice(createRequest);

        // Verify
        Mockito.verify(deviceRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(DeviceEntity.class));
    }

    @Test
    void givenValidUpdateDeviceRequest_whenDeviceExists_thenUpdateDeviceEntity() {
        // Given
        DeviceEntity device = DeviceEntity.builder()
                .id(1L)
                .serialNumber(UUID.randomUUID().toString().replace("-", ""))
                .brand("Brand")
                .model("model")
                .build();

        DeviceUpdateRequest updateRequest = new DeviceUpdateRequest(
                "updatedBrand",
                "updatedModel"
        );

        // When
        Mockito.when(deviceRepository.findById(device.getId()))
                .thenReturn(Optional.of(device));

        deviceService.updateDevice(device.getId(), updateRequest);

        // Verify
        Mockito.verify(deviceRepository, Mockito.times(1))
                .save(device);

        // Assert
        Assertions.assertEquals(updateRequest.brand(), device.getBrand());
        Assertions.assertEquals(updateRequest.model(), device.getModel());
    }

    @Test
    void givenNonExistingDeviceId_whenUpdateDevice_thenThrowDeviceNotFoundException() {
        // Given
        Long id = 1L;
        DeviceUpdateRequest mockUpdateRequest = new DeviceUpdateRequest(
                "NewBrand",
                "NewModel"
        );

        // When
        Mockito.when(deviceRepository.findById(id))
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(DeviceNotFoundException.class,
                () -> deviceService.updateDevice(id, mockUpdateRequest)
        );
    }


    @Test
    void givenExistingBrandAndModel_whenUpdateDevice_thenThrowDeviceAlreadyUpdatedException() {
        // Given
        Long id = 1L;
        DeviceEntity device = DeviceEntity.builder()
                .id(id)
                .brand("Brand")
                .model("Model")
                .serialNumber("12345")
                .build();

        DeviceUpdateRequest mockUpdateRequest = new DeviceUpdateRequest(
                "Brand",
                "Model"
        );

        // When
        Mockito.when(deviceRepository.findById(id))
                .thenReturn(Optional.of(device));

        // Then
        Assertions.assertThrows(DeviceAlreadyUpdatedException.class,
                () -> deviceService.updateDevice(id, mockUpdateRequest)
        );

        // Verify
        Mockito.verify(deviceRepository, Mockito.never()).save(ArgumentMatchers.any(DeviceEntity.class));
    }

    @Test
    void givenNewBrandAndModelAlreadyExists_whenUpdateDevice_thenThrowDeviceAlreadyExistsException() {
        // Given
        Long id = 1L;
        DeviceEntity device = DeviceEntity.builder()
                .id(id)
                .brand("Brand")
                .model("Model")
                .serialNumber("12345")
                .build();

        DeviceUpdateRequest mockUpdateRequest = new DeviceUpdateRequest("NewBrand", "NewModel");
        DeviceEntity existingDevice = DeviceEntity.builder()
                .id(2L)
                .brand("NewBrand")
                .model("NewModel")
                .build();

        // When
        Mockito.when(deviceRepository.findById(id)).thenReturn(Optional.of(device));
        Mockito.when(deviceRepository.findByBrandAndModel("NewBrand", "NewModel")).thenReturn(Optional.of(existingDevice));

        // Then
        Assertions.assertThrows(DeviceAlreadyExistsException.class,
                () -> deviceService.updateDevice(id, mockUpdateRequest)
        );

        // Verify
        Mockito.verify(deviceRepository, Mockito.never()).save(ArgumentMatchers.any(DeviceEntity.class));
    }

    @Test
    void givenValidDeviceId_whenDeleteDevice_thenSuccess() {
        // Given
        Long id = 1L;
        DeviceEntity deviceEntity = DeviceEntity.builder()
                .id(id)
                .brand("Brand")
                .model("Model")
                .serialNumber(UUID.randomUUID().toString().replace("-", ""))
                .build();

        Mockito.when(deviceRepository.findById(id)).thenReturn(Optional.of(deviceEntity));
        Mockito.when(deviceRepository.save(deviceEntity)).thenReturn(deviceEntity);

        // When
        deviceService.deleteDevice(id);

        // Then
        Assertions.assertNotNull(deviceEntity.getDeletedAt());
        Mockito.verify(deviceRepository, Mockito.times(1)).save(deviceEntity);
    }

    @Test
    void givenNonExistentDeviceId_whenDeleteDevice_thenThrowDeviceNotFoundException() {
        // Given
        Long id = 1L;
        Mockito.when(deviceRepository.findById(id)).thenReturn(Optional.empty());

        // Assert
        Assertions.assertThrows(DeviceNotFoundException.class,
                () -> deviceService.deleteDevice(id));

        // Verify
        Mockito.verify(deviceRepository, Mockito.never()).save(ArgumentMatchers.any());
    }

    @Test
    void givenAlreadyDeletedDevice_whenDeleteDevice_thenThrowDeviceAlreadyDeletedException() {
        // Given
        Long id = 1L;
        DeviceEntity deviceEntity = DeviceEntity.builder()
                .id(id)
                .brand("Brand")
                .model("Model")
                .serialNumber(UUID.randomUUID().toString().replace("-", ""))
                .deletedAt(LocalDateTime.now())
                .build();

        Mockito.when(deviceRepository.findById(id)).thenReturn(Optional.of(deviceEntity));

        // Assert
        Assertions.assertThrows(DeviceAlreadyDeletedException.class,
                () -> deviceService.deleteDevice(id));

        // Verify
        Mockito.verify(deviceRepository, Mockito.never()).save(ArgumentMatchers.any());
    }
}