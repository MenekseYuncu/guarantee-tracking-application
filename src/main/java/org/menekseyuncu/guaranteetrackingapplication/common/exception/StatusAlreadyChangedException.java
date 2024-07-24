package org.menekseyuncu.guaranteetrackingapplication.common.exception;

import java.io.Serial;


public class StatusAlreadyChangedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -766040117622969528L;

    public StatusAlreadyChangedException(String message) {
        super(message);
    }
}
