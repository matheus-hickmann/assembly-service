package com.assembly.assembly.service.v1.service.vote;

import com.assembly.assembly.service.v1.clients.DocumentValidationClient;
import com.assembly.assembly.service.enums.DocumentStatus;
import com.assembly.assembly.service.v1.clients.dtos.DocumentValidationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ValidateUserService {

    private static final DocumentStatus ABLE_TO_VOTE_STATUS = DocumentStatus.ABLE_TO_VOTE;

    private final DocumentValidationClient client;

    @Autowired
    public ValidateUserService(DocumentValidationClient client) {
        this.client = client;
    }

    public boolean execute(String document) {
        boolean able;
        try {
            DocumentValidationResponse votingStatus = client.getVotingStatus(document);
            log.info("{} is {}", document, votingStatus.getStatus());
            able = ABLE_TO_VOTE_STATUS.equals(votingStatus.getStatus());
            return able;
        } catch (Exception e) {
            log.error("Error to validate document {}", document, e);
        }
        return false;
    }

}
