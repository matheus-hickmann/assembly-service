package com.assembly.assembly.service.service.vote;

import com.assembly.assembly.service.enums.SessionStatus;
import com.assembly.assembly.service.enums.VoteEnum;
import com.assembly.assembly.service.model.Session;
import com.assembly.assembly.service.model.Vote;
import com.assembly.assembly.service.repository.VoteRepository;
import com.assembly.assembly.service.service.session.GetSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VoteService {

    private static final SessionStatus OPEN_STATUS = SessionStatus.OPENED;

    private final VoteRepository repository;
    private final GetSessionService getSessionService;

    @Autowired
    public VoteService(VoteRepository repository,GetSessionService getSessionService) {
        this.repository = repository;
        this.getSessionService = getSessionService;
    }

    public Vote execute(String userDocument, VoteEnum voteEnum, UUID sessionId) {
        Session session = getSessionService.execute(sessionId);

        if (!OPEN_STATUS.equals(session.getStatus())) {
            throw new IllegalArgumentException();
        }

        if (repository.existsBySessionIdAndUserDocument(sessionId, userDocument)) {
            throw new IllegalArgumentException();
        }

        return repository.save(Vote.builder()
                                       .session(session)
                                       .value(voteEnum)
                                       .userDocument(userDocument)
                                       .build()
        );

    }
}
