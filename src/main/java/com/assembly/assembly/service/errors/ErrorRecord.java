package com.assembly.assembly.service.errors;

public record ErrorRecord(
        ErrorCode code,
        String description
) {
}
