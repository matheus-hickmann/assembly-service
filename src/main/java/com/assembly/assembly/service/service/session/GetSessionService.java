package com.assembly.assembly.service.service.session;

import com.assembly.assembly.service.model.Session;
import com.assembly.assembly.service.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetSessionService {

    private final SessionRepository repository;

    @Autowired
    public GetSessionService(SessionRepository repository) {
        this.repository = repository;
    }

    public Session execute(UUID id) {
        Optional<Session> optionalSession = repository.findById(id);

        if (optionalSession.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return optionalSession.get();

    }
}
