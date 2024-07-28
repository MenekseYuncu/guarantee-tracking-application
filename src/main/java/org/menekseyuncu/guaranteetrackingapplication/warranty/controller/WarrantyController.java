package org.menekseyuncu.guaranteetrackingapplication.warranty.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.menekseyuncu.guaranteetrackingapplication.common.controller.response.BaseResponse;
import org.menekseyuncu.guaranteetrackingapplication.warranty.controller.mapper.WarrantyToWarrantyResponseMapper;
import org.menekseyuncu.guaranteetrackingapplication.warranty.controller.response.WarrantyResponse;
import org.menekseyuncu.guaranteetrackingapplication.warranty.model.domain.Warranty;
import org.menekseyuncu.guaranteetrackingapplication.warranty.service.WarrantyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing warranty-related operations.
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/warranty")
public class WarrantyController {

    private final WarrantyService warrantyService;
    private static final WarrantyToWarrantyResponseMapper warrantyToResponseMapper = WarrantyToWarrantyResponseMapper.INSTANCE;

    /**
     * Retrieves the warranty status of a specific device.
     *
     * @param deviceId the ID of the device
     * @return a {@link BaseResponse} containing the warranty status wrapped in a {@link WarrantyResponse}
     */
    @GetMapping("/{deviceId}")
    public BaseResponse<WarrantyResponse> getWarrantyStatus(
            @PathVariable @Positive Long deviceId) {

        WarrantyResponse warrantyResponse = warrantyToResponseMapper.map(
                warrantyService.getWarrantyStatus(deviceId)
        );

        return BaseResponse.successOf(warrantyResponse);
    }

    /**
     * Retrieves the list of warranties with expired status.
     *
     * @return a {@link BaseResponse} containing a list of expired warranties
     */
    @GetMapping("/expired")
    public BaseResponse<List<Warranty>> getExpiredWarranties() {
        List<Warranty> expiredWarranties = warrantyService.getExpiredWarranties();
        return BaseResponse.successOf(expiredWarranties);
    }

}