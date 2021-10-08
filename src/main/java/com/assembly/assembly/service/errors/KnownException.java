package com.assembly.assembly.service.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class KnownException extends RuntimeException {

    private final ErrorCode code;
    private final HttpStatus httpStatus;

    public KnownException(ErrorCode code,HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
