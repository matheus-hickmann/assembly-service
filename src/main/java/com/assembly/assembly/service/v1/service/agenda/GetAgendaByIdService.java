package com.assembly.assembly.service.v1.service.agenda;

import com.assembly.assembly.service.errors.exceptions.AgendaNotFoundException;
import com.assembly.assembly.service.model.Agenda;
import com.assembly.assembly.service.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetAgendaByIdService {

    private final AgendaRepository agendaRepository;

    @Autowired
    public GetAgendaByIdService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public Agenda execute(UUID id){
        Optional<Agenda> optionalAgenda = agendaRepository.findById(id);

        if (optionalAgenda.isEmpty()) {
            throw new AgendaNotFoundException();
        }

        return optionalAgenda.get();
    }
}
