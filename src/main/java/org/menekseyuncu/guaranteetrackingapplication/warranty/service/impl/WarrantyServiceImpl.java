package org.menekseyuncu.guaranteetrackingapplication.warranty.service.impl;

import lombok.RequiredArgsConstructor;
import org.menekseyuncu.guaranteetrackingapplication.warranty.exceptions.WarrantyNotFoundException;
import org.menekseyuncu.guaranteetrackingapplication.warranty.model.domain.Warranty;
import org.menekseyuncu.guaranteetrackingapplication.warranty.model.entity.WarrantyEntity;
import org.menekseyuncu.guaranteetrackingapplication.warranty.model.enums.WarrantyStatus;
import org.menekseyuncu.guaranteetrackingapplication.warranty.repository.WarrantyRepository;
import org.menekseyuncu.guaranteetrackingapplication.warranty.service.WarrantyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link WarrantyService} interface that provides methods to manage warranty information.
 * This includes checking and updating warranty statuses, creating new warranties, and retrieving warranty information.
 */
@Service
@RequiredArgsConstructor
class WarrantyServiceImpl implements WarrantyService {

    private final WarrantyRepository warrantyRepository;

    /**
     * Periodically checks the warranty status for all devices and updates their status to EXPIRED if the warranty has expired.
     * This method is scheduled to run daily at midnight.
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkWarrantyStatus() {
        List<WarrantyEntity> warranties = warrantyRepository.findAll();
        for (WarrantyEntity warranty : warranties) {
            if (isWarrantyExpired(warranty.getPurchaseDate())) {
                warranty.setStatus(WarrantyStatus.EXPIRED);
                warrantyRepository.save(warranty);
            }
        }
    }

    /**
     * Retrieves a list of warranties that have expired.
     *
     * @return a list of {@link Warranty} objects representing expired warranties
     */
    @Override
    public List<Warranty> getExpiredWarranties() {
        List<WarrantyEntity> warranties = warrantyRepository.findAll();
        return warranties.stream()
                .filter(warranty -> isWarrantyExpired(warranty.getPurchaseDate()))
                .map(warranty -> new Warranty(
                        warranty.getDeviceId(),
                        warranty.getDevice(),
                        warranty.getPurchaseDate(),
                        warranty.getStatus()
                ))
                .toList();
    }

    /**
     * Determines whether the warranty for a device is expired based on its purchase date.
     *
     * @param purchaseDate the purchase date of the device
     * @return {@code true} if the warranty is expired, {@code false} otherwise
     */
    private boolean isWarrantyExpired(LocalDate purchaseDate) {
        return ChronoUnit.YEARS.between(purchaseDate, LocalDate.now()) >= 2;
    }

    /**
     * Creates a new warranty for a device with the specified ID.
     * The warranty is initialized with the current date as the purchase date and ACTIVE status.
     *
     * @param deviceId the ID of the device for which to create a warranty
     */
    @Override
    public void createWarrantyForDevice(Long deviceId) {
        WarrantyEntity warranty = new WarrantyEntity();
        warranty.setDeviceId(deviceId);
        warranty.setPurchaseDate(LocalDate.now());
        warranty.setStatus(WarrantyStatus.ACTIVE);
        warrantyRepository.save(warranty);
    }

    /**
     * Retrieves the warranty status for a specific device based on its ID.
     *
     * @param deviceId the ID of the device whose warranty status is to be retrieved
     * @return a {@link Warranty} object containing the warranty information for the device
     * @throws WarrantyNotFoundException if no warranty is found for the specified device ID
     */
    @Override
    public Warranty getWarrantyStatus(Long deviceId) {
        Optional<WarrantyEntity> warranty = warrantyRepository.findByDeviceId(deviceId);
        if (warranty.isPresent()) {
            WarrantyEntity warrantyEntity = warranty.get();
            return new Warranty(
                    warrantyEntity.getDeviceId(),
                    warrantyEntity.getDevice(),
                    warrantyEntity.getPurchaseDate(),
                    warrantyEntity.getStatus()
            );
        } else {
            throw new WarrantyNotFoundException();
        }
    }
}