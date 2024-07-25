package org.menekseyuncu.guaranteetrackingapplication.common.exception;

import java.io.Serial;


public class AlreadyChangedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -766040117622969528L;

    public AlreadyChangedException(String message) {
        super(message);
    }
}
