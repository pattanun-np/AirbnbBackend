package org.armzbot.controller;


import lombok.Data;
import org.armzbot.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorAdviser {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {
        ErrorResponse response = new ErrorResponse();
        response.setError(e.getErrorMessage());
        response.setStatus(e.getErrorCode());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(e.getErrorCode()));
    }

    @Data
    public static class ErrorResponse {

        private LocalDateTime timestamp = LocalDateTime.now();

        private int status;

        private String error;

    }
}
