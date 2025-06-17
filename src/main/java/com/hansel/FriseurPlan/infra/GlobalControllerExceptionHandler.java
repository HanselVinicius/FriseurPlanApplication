package com.hansel.FriseurPlan.infra;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    public record ExceptionResponse(String message) {}

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError() != null ?
            ex.getBindingResult().getFieldError().getDefaultMessage() : "Invalid input";
        ExceptionResponse exceptionResponse = new ExceptionResponse(errorMessage);
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

}
