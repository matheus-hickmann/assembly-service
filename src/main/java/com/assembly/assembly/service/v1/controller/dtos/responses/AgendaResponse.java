package com.assembly.assembly.service.v1.controller.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaResponse {

    UUID id;
    String name;
    String description;
    String creatorDocument;

}
