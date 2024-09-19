package com.grabieckacper.talktalk.handler;

import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestControllerHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { EntityExistsException.class })
    protected ResponseEntity<Object> handle409Conflict(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        ObjectError error = ex.getBindingResult()
                .getAllErrors()
                .getFirst();
        String fieldName = ((FieldError) error).getField();
        String message = error.getDefaultMessage();
        String body = fieldName + ": " + message;

        return handleExceptionInternal(ex, body, headers, status, request);
    }
}
