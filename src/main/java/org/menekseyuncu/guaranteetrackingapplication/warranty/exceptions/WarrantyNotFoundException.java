package org.menekseyuncu.guaranteetrackingapplication.warranty.exceptions;

import org.menekseyuncu.guaranteetrackingapplication.common.exception.NotFoundException;

import java.io.Serial;

/**
 * Exception thrown when a warranty for a given device ID is not found.
 */
public class WarrantyNotFoundException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = 8564071812041301216L;

    /**
     * Constructs a new {@code WarrantyNotFoundException} with a default detail message.
     */
    public WarrantyNotFoundException() {
        super("Warranty for device ID not found.");
    }
}