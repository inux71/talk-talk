package com.grabieckacper.talktalk.handler;

import com.grabieckacper.talktalk.response.ErrorResponse;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestControllerHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { EntityExistsException.class })
    protected ResponseEntity<Object> handle409Conflict(RuntimeException ex, WebRequest request) {
        List<String> messages = new ArrayList<>();
        messages.add(ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), messages);

        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        List<String> messages = new ArrayList<>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach((error) -> messages.add(error.getDefaultMessage()));

        ErrorResponse errorResponse = new ErrorResponse(status.value(), messages);

        return handleExceptionInternal(ex, errorResponse, headers, status, request);
    }
}
