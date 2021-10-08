package com.assembly.assembly.service.v1.controller.dtos;

import com.assembly.assembly.service.model.Session;

public record SessionResultRecord(
        Session session,
        long votesInFavor,
        long votesAgainst
) {
}
