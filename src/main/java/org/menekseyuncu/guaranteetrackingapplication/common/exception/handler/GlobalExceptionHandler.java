package org.menekseyuncu.guaranteetrackingapplication.common.exception.handler;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.menekseyuncu.guaranteetrackingapplication.common.controller.response.ErrorResponse;
import org.menekseyuncu.guaranteetrackingapplication.common.exception.AlreadyExistException;
import org.menekseyuncu.guaranteetrackingapplication.common.exception.NotFoundException;
import org.menekseyuncu.guaranteetrackingapplication.common.exception.AlreadyChangedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the application.
 * Handles various exceptions thrown during the processing of HTTP requests.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link MethodArgumentNotValidException} thrown when validation of method arguments fails.
     *
     * @param ex the exception that was thrown
     * @return a {@link ResponseEntity} containing the error details
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error("Validation failed: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.failureOf(HttpStatus.BAD_REQUEST, "Validation failed");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link ConstraintViolationException} thrown when a constraint violation occurs.
     *
     * @param ex the exception that was thrown
     * @return a {@link ResponseEntity} containing the error details
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("Constraint violation: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.failureOf(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link IllegalArgumentException} thrown when an illegal argument is provided.
     *
     * @param exception the exception that was thrown
     * @return a {@link ResponseEntity} containing the error details
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(final IllegalArgumentException exception) {
        log.error("Illegal argument: {}", exception.getMessage(), exception);
        ErrorResponse errorResponse = ErrorResponse.failureOf(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link AlreadyChangedException} thrown when an entity that has already been changed is modified again.
     *
     * @param exception the exception that was thrown
     * @return a {@link ResponseEntity} containing the error details
     */
    @ExceptionHandler(AlreadyChangedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleAlreadyChangedException(final AlreadyChangedException exception) {
        log.error("Entity already changed: {}", exception.getMessage(), exception);
        ErrorResponse errorResponse = ErrorResponse.failureOf(HttpStatus.CONFLICT, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handles {@link AlreadyExistException} thrown when an entity that already exists is being created or updated.
     *
     * @param exception the exception that was thrown
     * @return a {@link ResponseEntity} containing the error details
     */
    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleAlreadyExistException(final AlreadyExistException exception) {
        log.error("Entity already exists: {}", exception.getMessage(), exception);
        ErrorResponse errorResponse = ErrorResponse.failureOf(HttpStatus.CONFLICT, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handles {@link NotFoundException} thrown when an entity is not found.
     *
     * @param exception the exception that was thrown
     * @return a {@link ResponseEntity} containing the error details
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final NotFoundException exception) {
        log.error("Entity not found: {}", exception.getMessage(), exception);
        ErrorResponse errorResponse = ErrorResponse.failureOf(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}