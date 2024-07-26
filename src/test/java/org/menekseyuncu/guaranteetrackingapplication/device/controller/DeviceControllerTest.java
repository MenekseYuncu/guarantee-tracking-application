package org.menekseyuncu.guaranteetrackingapplication.device.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.menekseyuncu.guaranteetrackingapplication.ControllerTest;
import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceCreateRequest;
import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceUpdateRequest;
import org.menekseyuncu.guaranteetrackingapplication.device.exceptions.DeviceAlreadyExistsException;
import org.menekseyuncu.guaranteetrackingapplication.device.exceptions.DeviceNotFoundException;
import org.menekseyuncu.guaranteetrackingapplication.device.service.DeviceService;
import org.menekseyuncu.guaranteetrackingapplication.device.service.mapper.DeviceCreateRequestToDeviceMapper;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Random;

@WebMvcTest(controllers = DeviceController.class)
class DeviceControllerTest extends ControllerTest {

    @MockBean
    private DeviceService deviceService;

    private static final DeviceCreateRequestToDeviceMapper deviceCreateRequestToDeviceMapper = DeviceCreateRequestToDeviceMapper.INSTANCE;

    private final static String BASE_URL = "/api/v1/device";


    @Test
    void givenValidCreateDeviceRequest_whenCreateDevice_thenReturnsSuccess() throws Exception {
        // Given
        DeviceCreateRequest mockCreateRequest = new DeviceCreateRequest(
                "Brand",
                "Model"
        );

        // When
        deviceCreateRequestToDeviceMapper.map(mockCreateRequest);

        Mockito.doNothing().when(deviceService).createDevice(Mockito.any(DeviceCreateRequest.class));

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockCreateRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify
        Mockito.verify(deviceService, Mockito.times(1))
                .createDevice(mockCreateRequest);
    }

    @Test
    void givenInvalidCreateDeviceRequest_whenCreateDevice_thenReturnBadRequest() throws Exception {
        // Given
        DeviceCreateRequest mockCreateRequest = new DeviceCreateRequest(
                null,
                "Model"
        );

        // When
        deviceCreateRequestToDeviceMapper.map(mockCreateRequest);

        Mockito.doThrow(new DeviceNotFoundException()).when(deviceService)
                .createDevice(mockCreateRequest);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockCreateRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        // Verify
        Mockito.verifyNoInteractions(deviceService);
    }


    @Test
    void givenValidUpdateDeviceRequest_whenDeviceFound_thenReturnSuccess() throws Exception {
        //Given
        Long id = new Random().nextLong();

        // When
        DeviceUpdateRequest mockUpdateRequest = new DeviceUpdateRequest(
                "Brand",
                "model"
        );

        Mockito.doNothing().when(deviceService).updateDevice(id, mockUpdateRequest);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockUpdateRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(true));

        // Verify
        Mockito.verify(deviceService).updateDevice(id, mockUpdateRequest);
    }

    @Test
    void givenInvalidUpdateDeviceRequest_whenDeviceUpdateRequest_thenReturnBadRequest() throws Exception {
        //Given
        Long id = new Random().nextLong();

        // When
        DeviceUpdateRequest mockUpdateRequest = new DeviceUpdateRequest(
                null,
                null
        );

        //When
        Mockito.doThrow(new DeviceNotFoundException()).when(deviceService)
                .updateDevice(id, mockUpdateRequest);

        //Then
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockUpdateRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        // Verify
        Mockito.verifyNoInteractions(deviceService);
    }

    @Test
    void givenUpdateDeviceIdNull_whenDevice_thenReturnNotFoundException() throws Exception {
        // Given
        Long id = null;


        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        // Verify
        Mockito.verifyNoInteractions(deviceService);
    }

    @Test
    void givenInvalidUpdateRequest_whenDeviceIdNotFound_thenReturnBadRequest() throws Exception {
        //Given
        Long id = new Random().nextLong();

        // When
        DeviceUpdateRequest mockUpdateRequest = new DeviceUpdateRequest(
                "Brand",
                "model"
        );

        Mockito.doThrow(DeviceNotFoundException.class)
                .when(deviceService)
                .updateDevice(id, mockUpdateRequest);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        // Verify
        Mockito.verifyNoInteractions(deviceService);
    }

    @Test
    void givenUpdateDeviceConflict_whenUpdateDevice_thenReturnDeviceAlreadyExistsException() throws Exception {
        // Given
        Long id = Math.abs(new Random().nextLong());

        // When
        DeviceUpdateRequest mockUpdateRequest = new DeviceUpdateRequest(
                "Brand",
                "model"
        );

        Mockito.doThrow(new DeviceAlreadyExistsException()).when(deviceService)
                .updateDevice(Mockito.any(Long.class), Mockito.any(DeviceUpdateRequest.class));

        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockUpdateRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isConflict());

        // Verify
        Mockito.verify(deviceService, Mockito.times(1))
                .updateDevice(Mockito.any(Long.class), Mockito.any(DeviceUpdateRequest.class));
    }


    @Test
    void givenValidDeleteDeviceId_whenDeviceFound_thenReturnSuccess() throws Exception {
        // Given
        Long id = Math.abs(new Random().nextLong());

        // When
        Mockito.doNothing().when(deviceService).deleteDevice(id);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(true));

        // Verify
        Mockito.verify(deviceService).deleteDevice(id);
    }

    @Test
    void givenInvalidDeleteDeviceId_whenDeviceNotExist_thenReturnNotFound() throws Exception {
        // Given
        Long id = Math.abs(new Random().nextLong());

        // When
        Mockito.doThrow(DeviceNotFoundException.class)
                .when(deviceService)
                .deleteDevice(id);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        // Verify
        Mockito.verify(deviceService).deleteDevice(id);
    }

    @Test
    void givenDeviceIdNull_whenDeleteDeviceNotFound_thenException() throws Exception {
        // Given
        Long id = null;

        // When
        Mockito.doThrow(DeviceNotFoundException.class)
                .when(deviceService)
                .deleteDevice(id);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        // Verify
        Mockito.verifyNoInteractions(deviceService);
    }

    @Test
    void givenDeviceIdNotFound_whenDeleteDevice_thenReturnNotFoundException() throws Exception {
        // Given
        Long id = Math.abs(new Random().nextLong());

        // When
        Mockito.doThrow(DeviceNotFoundException.class)
                .when(deviceService)
                .deleteDevice(id);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        // Verify
        Mockito.verify(deviceService, Mockito.times(1)).deleteDevice(id);
    }

}