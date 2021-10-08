package com.assembly.assembly.service.errors.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNKNOWN_EXCEPTION("Unexpected error")
    ;

    private final String description;

    ErrorCode(String description) {
        this.description = description;
    }

}
