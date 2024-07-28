package org.menekseyuncu.guaranteetrackingapplication.common.exception;

import java.io.Serial;

/**
 * Exception thrown when a requested resource or entity is not found.
 */
public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    /**
     * Constructs a new {@code NotFoundException} with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by the {@link #getMessage()} method
     */
    public NotFoundException(String message) {
        super(message);
    }
}