package com.assembly.assembly.service.errors.exceptions;

import com.assembly.assembly.service.errors.ErrorCode;
import com.assembly.assembly.service.errors.KnownException;
import org.springframework.http.HttpStatus;

public class InvalidDocumentException extends KnownException {

    public InvalidDocumentException() {
        super(ErrorCode.INVALID_DOCUMENT,HttpStatus.BAD_REQUEST);
    }
}
