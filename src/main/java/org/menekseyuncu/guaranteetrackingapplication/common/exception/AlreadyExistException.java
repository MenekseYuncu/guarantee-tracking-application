package org.menekseyuncu.guaranteetrackingapplication.common.exception;

import java.io.Serial;

public class AlreadyExistException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -8650489690571070800L;

    public AlreadyExistException(String message) {
        super(message);
    }
}
