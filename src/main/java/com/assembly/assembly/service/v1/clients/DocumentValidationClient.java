package com.assembly.assembly.service.v1.clients;

import com.assembly.assembly.service.v1.clients.dtos.DocumentValidationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "document-api", url = "${document-api.url}")
public interface DocumentValidationClient {

    @GetMapping("/{document}")
    DocumentValidationResponse getVotingStatus(@PathVariable("document") String documentNumber);

}
