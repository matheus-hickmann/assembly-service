package com.assembly.assembly.service.errors;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNKNOWN_EXCEPTION("Unexpected error"),
    AGENDA_NOT_FOUND("Agenda not found"),
    SESSION_NOT_FOUND("Session not found"),
    SESSION_OPENED("This agenda contains an opened session"),
    INVALID_DOCUMENT("Document is invalid"),
    SESSION_VOTE("Unable to vote in this session"),
    ALREADY_VOTED("User already voted in this session")
    ;

    private final String description;

    ErrorCode(String description) {
        this.description = description;
    }

}
