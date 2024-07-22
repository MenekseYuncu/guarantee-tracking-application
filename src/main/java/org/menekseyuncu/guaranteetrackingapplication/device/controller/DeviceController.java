package org.menekseyuncu.guaranteetrackingapplication.device.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.menekseyuncu.guaranteetrackingapplication.common.controller.response.BaseResponse;
import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceCreateRequest;
import org.menekseyuncu.guaranteetrackingapplication.device.service.DeviceService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping
    public BaseResponse<Void> createDevice(
            @Valid @RequestBody DeviceCreateRequest request
    ) {
        deviceService.createDevice(request);
        return BaseResponse.SUCCESS;
    }

}
