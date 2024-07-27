package org.menekseyuncu.guaranteetrackingapplication.device.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.menekseyuncu.guaranteetrackingapplication.EndToEndTest;
import org.menekseyuncu.guaranteetrackingapplication.TestContainer;
import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceCreateRequest;
import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceUpdateRequest;
import org.menekseyuncu.guaranteetrackingapplication.device.model.domain.Device;
import org.menekseyuncu.guaranteetrackingapplication.device.model.entity.DeviceEntity;
import org.menekseyuncu.guaranteetrackingapplication.device.repository.DeviceRepository;
import org.menekseyuncu.guaranteetrackingapplication.device.service.mapper.DeviceToDeviceEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

class DeviceEndToEndTest extends EndToEndTest implements TestContainer {

    @Autowired
    private DeviceRepository deviceRepository;

    private static final DeviceToDeviceEntityMapper deviceToDeviceEntityMapper = DeviceToDeviceEntityMapper.INSTANCE;

    private final static String BASE_URL = "/api/v1/device";


    @Test
    void givenValidCreateDeviceRequest_whenCreateDevice_thenReturnsSuccess() throws Exception {
        // Given
        DeviceCreateRequest mockCreateRequest = new DeviceCreateRequest(
                "Brand",
                "Model"
        );

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockCreateRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void givenValidUpdateDeviceRequest_whenDeviceFound_thenReturnSuccess() throws Exception {
        // Given
        Device device = Device.builder()
                .id(1L)
                .serialNumber(UUID.randomUUID().toString().replace("-", ""))
                .brand("Brand")
                .model("model")
                .build();

        DeviceEntity deviceEntity = deviceToDeviceEntityMapper.map(device);
        deviceRepository.save(deviceEntity);

        // When
        DeviceUpdateRequest mockUpdateRequest = new DeviceUpdateRequest(
                "updatedBrand",
                "updatedModel"
        );

        // Then
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/{id}", deviceEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockUpdateRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(true));
    }


    @Test
    void givenValidDeleteDeviceId_whenDeviceFound_thenReturnSuccess() throws Exception {
        // Given
        Device device = Device.builder()
                .id(1L)
                .serialNumber(UUID.randomUUID().toString().replace("-", ""))
                .brand("Brand")
                .model("model")
                .build();

        DeviceEntity deviceEntity = deviceToDeviceEntityMapper.map(device);
        deviceRepository.save(deviceEntity);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/{id}", deviceEntity.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(true));
    }

}
