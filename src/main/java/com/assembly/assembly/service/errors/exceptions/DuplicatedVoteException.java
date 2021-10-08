package com.assembly.assembly.service.errors.exceptions;

import com.assembly.assembly.service.errors.ErrorCode;
import com.assembly.assembly.service.errors.KnownException;
import org.springframework.http.HttpStatus;

public class DuplicatedVoteException extends KnownException {

    public DuplicatedVoteException() {
        super(ErrorCode.ALREADY_VOTED,HttpStatus.CONFLICT);
    }
}
