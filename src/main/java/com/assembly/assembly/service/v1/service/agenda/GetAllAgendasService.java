package com.assembly.assembly.service.v1.service.agenda;

import com.assembly.assembly.service.model.Agenda;
import com.assembly.assembly.service.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllAgendasService {

    private final AgendaRepository repository;

    @Autowired
    public GetAllAgendasService(AgendaRepository repository) {
        this.repository = repository;
    }

    public List<Agenda> execute() {
        return repository.findAll();
    }
}
