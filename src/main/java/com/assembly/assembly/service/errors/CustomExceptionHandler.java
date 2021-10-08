package com.assembly.assembly.service.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler( value = KnownException.class)
    ResponseEntity<ErrorRecord> handleKnownException(KnownException e,WebRequest request) {
        log.info("Returning Exception: {}", e);
        return new ResponseEntity<ErrorRecord>(new ErrorRecord(
                e.getCode(),
                e.getCode().getDescription()
        ), e.getHttpStatus());
    }

    @ExceptionHandler( value = RuntimeException.class)
    ResponseEntity<ErrorRecord> handleUnknownException(RuntimeException e,WebRequest request) {
        log.error("Unexpected Exception: {}", e);
        return new ResponseEntity<ErrorRecord>(new ErrorRecord(
                ErrorCode.UNKNOWN_EXCEPTION,
                ErrorCode.UNKNOWN_EXCEPTION.getDescription()
        ),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
