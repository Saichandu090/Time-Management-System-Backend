package com.tms.time_management_system.exception;

import com.tms.time_management_system.dto.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<JsonResponse> runTimeException(RuntimeException exception)
    {
        return new ResponseEntity<>(new JsonResponse(false, exception.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<JsonResponse> userNotFound(UserNotFoundException exception)
    {
        return new ResponseEntity<>(new JsonResponse(false,exception.getMessage(),null),HttpStatus.BAD_REQUEST);
    }
}
