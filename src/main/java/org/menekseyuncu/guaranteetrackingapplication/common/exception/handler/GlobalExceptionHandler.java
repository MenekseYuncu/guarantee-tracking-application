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

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.failureOf(HttpStatus.BAD_REQUEST, "Validation failed");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.failureOf(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(final IllegalArgumentException exception) {
        log.error(exception.getMessage(), exception);
        ErrorResponse errorResponse = ErrorResponse.failureOf(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyChangedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleRmaStatusAlreadyChangedException(final AlreadyChangedException exception) {
        log.error(exception.getMessage(), exception);
        ErrorResponse errorResponse = ErrorResponse.failureOf(HttpStatus.CONFLICT, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleRmaAlreadyExistException(final AlreadyExistException exception) {
        log.error(exception.getMessage(), exception);
        ErrorResponse errorResponse = ErrorResponse.failureOf(HttpStatus.CONFLICT, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleRmaNotFoundExistException(final NotFoundException exception) {
        log.error(exception.getMessage(), exception);
        ErrorResponse errorResponse = ErrorResponse.failureOf(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}