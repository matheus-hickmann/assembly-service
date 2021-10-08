package com.assembly.assembly.service.errors.exceptions;

import com.assembly.assembly.service.errors.ErrorCode;
import com.assembly.assembly.service.errors.KnownException;
import org.springframework.http.HttpStatus;

public class AgendaNotFoundException extends KnownException {

    public AgendaNotFoundException() {
        super(ErrorCode.AGENDA_NOT_FOUND,HttpStatus.NOT_FOUND);
    }
}
