package com.assembly.assembly.service.service.session;

import com.assembly.assembly.service.enums.SessionStatus;
import com.assembly.assembly.service.errors.exceptions.OpenedSessionException;
import com.assembly.assembly.service.model.Agenda;
import com.assembly.assembly.service.model.Session;
import com.assembly.assembly.service.repository.SessionRepository;
import com.assembly.assembly.service.service.agenda.GetAgendaByIdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class OpenSessionService {

    private static final Integer DEFAULT_DURATION = 1;
    private static final SessionStatus OPEN_STATUS = SessionStatus.OPENED;

    private final SessionRepository repository;
    private final GetAgendaByIdService getAgendaByIdService;

    @Autowired
    public OpenSessionService(SessionRepository repository,GetAgendaByIdService getAgendaByIdService) {
        this.repository = repository;
        this.getAgendaByIdService = getAgendaByIdService;
    }

    public Session execute(UUID id, Integer duration) {
        if (agendaContainsOpenedSession(id)) {
            throw new OpenedSessionException();
        }
        LocalDateTime now = LocalDateTime.now();
        Agenda agenda = getAgendaByIdService.execute(id);

        return repository.save(Session.builder()
                                       .agenda(agenda)
                                       .startDate(now)
                                       .endDate(calculateEndDate(now, duration))
                                       .build()
        );
    }

    private boolean agendaContainsOpenedSession(UUID id) {
        List<Session> sessions = repository.getByAgendaId(id);

        return sessions.stream().anyMatch(it -> OPEN_STATUS.equals(it.getStatus()));
    }

    private LocalDateTime calculateEndDate(LocalDateTime now,Integer duration) {
        return LocalDateTime.from(now.plus(Duration.of(Objects.requireNonNullElse(duration,DEFAULT_DURATION),ChronoUnit.MINUTES)));

    }
}
