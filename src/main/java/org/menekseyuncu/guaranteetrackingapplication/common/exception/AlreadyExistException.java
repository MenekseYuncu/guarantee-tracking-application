package org.menekseyuncu.guaranteetrackingapplication.common.exception;

import java.io.Serial;

/**
 * Exception thrown when an attempt is made to create or update an entity that already exists.
 */
public class AlreadyExistException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -8650489690571070800L;

    /**
     * Constructs a new {@code AlreadyExistException} with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by the {@link #getMessage()} method
     */
    public AlreadyExistException(String message) {
        super(message);
    }
}