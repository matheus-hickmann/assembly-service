package com.assembly.assembly.service.v1.controller.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SessionResultResponse {

    private long votesInFavor;
    private long votesAgainst;

}
