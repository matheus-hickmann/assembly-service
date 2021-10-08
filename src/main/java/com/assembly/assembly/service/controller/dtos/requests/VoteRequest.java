package com.assembly.assembly.service.controller.dtos.requests;

import com.assembly.assembly.service.enums.VoteEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequest {

    @NotBlank
    private String document;

    @NotNull
    private VoteEnum vote;

}
