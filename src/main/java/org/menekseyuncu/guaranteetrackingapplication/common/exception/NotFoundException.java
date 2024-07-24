package org.menekseyuncu.guaranteetrackingapplication.common.exception;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public NotFoundException(String message) {
        super(message);
    }
}
