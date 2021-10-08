package com.assembly.assembly.service.v1.controller.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionResultResponse {

    private long votesInFavor;
    private long votesAgainst;

}
