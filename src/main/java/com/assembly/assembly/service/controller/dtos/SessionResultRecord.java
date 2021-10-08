package com.assembly.assembly.service.controller.dtos;

import com.assembly.assembly.service.model.Session;

public record SessionResultRecord(
        Session session,
        long votesInFavor,
        long votesAgainst
) {
}
