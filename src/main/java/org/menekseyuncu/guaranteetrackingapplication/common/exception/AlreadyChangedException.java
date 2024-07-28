package org.menekseyuncu.guaranteetrackingapplication.common.exception;

import java.io.Serial;


/**
 * Exception thrown when an attempt is made to modify an entity that has already been changed.
 */
public class AlreadyChangedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -766040117622969528L;

    /**
     * Constructs a new {@code AlreadyChangedException} with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by the {@link #getMessage()} method
     */
    public AlreadyChangedException(String message) {
        super(message);
    }
}