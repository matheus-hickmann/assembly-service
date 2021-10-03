package com.assembly.assembly.service.service.agenda;

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

        return optionalAgenda.get();
    }
}
