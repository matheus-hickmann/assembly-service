package com.assembly.assembly.service.v1.service.vote;

import com.assembly.assembly.service.v1.controller.dtos.SessionResultRecord;
import com.assembly.assembly.service.enums.VoteEnum;
import com.assembly.assembly.service.model.Session;
import com.assembly.assembly.service.model.Vote;
import com.assembly.assembly.service.repository.VoteRepository;
import com.assembly.assembly.service.v1.service.session.GetSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ComputeVoteService {

    private final GetSessionService getSessionService;
    private final VoteRepository repository;

    @Autowired
    public ComputeVoteService(GetSessionService getSessionService,VoteRepository repository) {
        this.getSessionService = getSessionService;
        this.repository = repository;
    }

    public SessionResultRecord execute(UUID sessionId) {
        Session session = getSessionService.execute(sessionId);
        List<Vote> votes = repository.findBySessionId(sessionId);

        return new SessionResultRecord(
                session,
                countVotes(votes, VoteEnum.YES),
                countVotes(votes, VoteEnum.NO)
        );
    }

    private long countVotes(List<Vote> votes,VoteEnum value) {
        return votes.stream().filter(vote -> value.equals(vote.getValue())).count();

    }


}
