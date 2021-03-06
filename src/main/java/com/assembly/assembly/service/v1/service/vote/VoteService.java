package com.assembly.assembly.service.v1.service.vote;

import com.assembly.assembly.service.enums.SessionStatus;
import com.assembly.assembly.service.enums.VoteEnum;
import com.assembly.assembly.service.errors.exceptions.DuplicatedVoteException;
import com.assembly.assembly.service.errors.exceptions.InvalidDocumentException;
import com.assembly.assembly.service.errors.exceptions.VoteInSessionException;
import com.assembly.assembly.service.model.Session;
import com.assembly.assembly.service.model.Vote;
import com.assembly.assembly.service.repository.VoteRepository;
import com.assembly.assembly.service.v1.service.session.GetSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VoteService {

    private static final SessionStatus OPEN_STATUS = SessionStatus.OPENED;

    private final VoteRepository repository;
    private final GetSessionService getSessionService;
    private final ValidateUserService validateUserService;

    @Autowired
    public VoteService(VoteRepository repository,GetSessionService getSessionService,ValidateUserService validateUserService) {
        this.repository = repository;
        this.getSessionService = getSessionService;
        this.validateUserService = validateUserService;
    }

    public Vote execute(String userDocument, VoteEnum voteEnum, UUID sessionId) {
        if (!validateUserService.execute(userDocument)) {
            throw new InvalidDocumentException();
        }
        Session session = getSessionService.execute(sessionId);

        if (!OPEN_STATUS.equals(session.getStatus())) {
            throw new VoteInSessionException();
        }

        if (repository.existsBySessionIdAndUserDocument(sessionId, userDocument)) {
            throw new DuplicatedVoteException();
        }

        return repository.save(Vote.builder()
                                       .session(session)
                                       .value(voteEnum)
                                       .userDocument(userDocument)
                                       .build()
        );

    }
}
