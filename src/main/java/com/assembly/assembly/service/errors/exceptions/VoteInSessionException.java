package com.assembly.assembly.service.errors.exceptions;

import com.assembly.assembly.service.errors.ErrorCode;
import com.assembly.assembly.service.errors.KnownException;
import org.springframework.http.HttpStatus;

public class VoteInSessionException extends KnownException {

    public VoteInSessionException() {
        super(ErrorCode.SESSION_VOTE,HttpStatus.BAD_REQUEST);
    }
}
