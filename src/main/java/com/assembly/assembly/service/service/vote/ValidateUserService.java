package com.assembly.assembly.service.service.vote;

import com.assembly.assembly.service.clients.DocumentValidationClient;
import com.assembly.assembly.service.enums.DocumentStatus;
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
            able = ABLE_TO_VOTE_STATUS.equals(client.getVotingStatus(document).getStatus());
            return able;
        } catch (Exception e) {
            log.error("Error to validate document {}", document, e);
        }
        return false;
    }

}
