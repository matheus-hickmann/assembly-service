package com.assembly.assembly.service.service.agenda;

import com.assembly.assembly.service.model.Agenda;
import com.assembly.assembly.service.repository.AgendaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateAgendaService {

    private final AgendaRepository repository;

    @Autowired
    public CreateAgendaService(
            AgendaRepository repository
    ) {
        this.repository = repository;
    }

    public Agenda execute(String name,String description,String creatorDocument) {
        return repository.save( Agenda.builder()
                                        .name( name )
                                        .creatorDocument( creatorDocument )
                                        .description( description )
                                        .build()
        );
    }

}
