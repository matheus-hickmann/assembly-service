package com.assembly.assembly.service.v1.clients.dtos;

import com.assembly.assembly.service.enums.DocumentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentValidationResponse {

    private DocumentStatus status;
}
