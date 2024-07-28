package org.menekseyuncu.guaranteetrackingapplication.common.controller.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Represents an error response returned to the client.
 * Extends {@link BaseResponse} to include error-specific information.
 */
public class ErrorResponse extends BaseResponse<String> {

    /**
     * Constructs a new {@link ErrorResponse} with the given HTTP status and message.
     *
     * @param httpStatus the HTTP status to set for the response
     * @param message    the error message to include in the response
     */
    public ErrorResponse(HttpStatus httpStatus, String message) {
        super(LocalDateTime.now(), httpStatus, false, message);
    }

    /**
     * Creates a new {@link ErrorResponse} instance.
     *
     * @param httpStatus the HTTP status to set for the response
     * @param message    the error message to include in the response
     * @return a new {@link ErrorResponse} instance
     */
    public static ErrorResponse failureOf(HttpStatus httpStatus, String message) {
        return new ErrorResponse(httpStatus, message);
    }
}
