package com.assembly.assembly.service.errors.exceptions;

import com.assembly.assembly.service.errors.ErrorCode;
import com.assembly.assembly.service.errors.KnownException;
import org.springframework.http.HttpStatus;

public class SessionNotFoundException extends KnownException {

    public SessionNotFoundException() {
        super(ErrorCode.SESSION_NOT_FOUND,HttpStatus.NOT_FOUND);
    }
}
