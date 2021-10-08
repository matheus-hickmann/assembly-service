package com.assembly.assembly.service.v1.service.agenda;

import com.assembly.assembly.service.errors.exceptions.AgendaNotFoundException;
import com.assembly.assembly.service.model.Agenda;
import com.assembly.assembly.service.repository.AgendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAgendaByIdServiceTests {

    @Mock
    private AgendaRepository repository;

    private GetAgendaByIdService getAgendaByIdService;

    @BeforeEach
    public void init() {
        getAgendaByIdService = new GetAgendaByIdService(repository);
    }

    @Test
    public void givenAgendaDoesNotExists_shouldThrowAgendaNotFoundException() {
        //Arrange
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        //Act/Assert
        assertThrows(AgendaNotFoundException.class, () -> getAgendaByIdService.execute(id) );

    }

    @Test
    public void givenAgendaExists_shouldReturnAgendaObject() {
        //Arrange
        UUID id = UUID.randomUUID();
        Agenda expected = Agenda.builder()
                .creatorDocument("Testing Doc")
                .description("Testing Description")
                .name("Testing Name")
                .build();
        when(repository.findById(id)).thenReturn(Optional.of(expected));

        //Act
        Agenda result = getAgendaByIdService.execute(id);

        //Assert
        assertEquals(expected, result, "Objects should be equals");

    }
}
