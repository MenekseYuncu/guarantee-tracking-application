package org.menekseyuncu.guaranteetrackingapplication.warranty.controller.response;

import org.menekseyuncu.guaranteetrackingapplication.warranty.model.enums.WarrantyStatus;

import java.time.LocalDate;

public record WarrantyResponse(

        Long deviceId,
        WarrantyStatus status,
        LocalDate purchaseDate
) {
}
