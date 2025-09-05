package com.example.hello_spring.advice;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // @RequestBody 유효성 실패 (예: POST /todos 에서 title 공백)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleBodyValidation(MethodArgumentNotValidException e) {
        var fe = e.getBindingResult().getFieldError();
        var field = fe != null ? fe.getField() : null;
        var message = fe != null ? fe.getDefaultMessage() : "Validation failed";
        return Map.of(
                "error", "Bad Request",
                "field", field,
                "message", message
        );
    }

    // @RequestParam 유효성 실패 (예: /echo?msg=  )
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleConstraint(ConstraintViolationException e) {
        var v = e.getConstraintViolations().stream().findFirst();
        var path = v.map(cv -> cv.getPropertyPath().toString()).orElse(null);
        var message = v.map(cv -> cv.getMessage()).orElse("Validation failed");
        return Map.of(
                "error", "Bad Request",
                "field", path,
                "message", message
        );
    }

    // 필수 파라미터 누락 (예: /echo 호출 시 msg 자체를 안 보냄)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleMissingParam(MissingServletRequestParameterException e) {
        return Map.of(
                "error", "Bad Request",
                "field", e.getParameterName(),
                "message", "Required parameter is missing"
        );
    }
}
