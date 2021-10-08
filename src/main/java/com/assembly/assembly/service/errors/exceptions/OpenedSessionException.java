package com.assembly.assembly.service.errors.exceptions;

import com.assembly.assembly.service.errors.ErrorCode;
import com.assembly.assembly.service.errors.KnownException;
import org.springframework.http.HttpStatus;

public class OpenedSessionException extends KnownException {

    public OpenedSessionException() {
        super(ErrorCode.SESSION_OPENED,HttpStatus.BAD_REQUEST);
    }
}
