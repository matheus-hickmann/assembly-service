package com.assembly.assembly.service.v1.service.agenda;

import com.assembly.assembly.service.model.Agenda;
import com.assembly.assembly.service.repository.AgendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateAgendaServiceTests {

    @Mock
    private AgendaRepository repository;

    private CreateAgendaService createAgendaService;

    @BeforeEach
    public void init() {
        createAgendaService = new CreateAgendaService(repository);
    }

    @Test
    public void givenReceivesARequest_shouldCassRepositorySaveAndReturnEquivalentObjectWhitId() {
        //Arrange
        UUID id = UUID.randomUUID();
        Agenda expected = Agenda.builder()
                .id(id)
                .creatorDocument("12345678910")
                .description("Testing description")
                .name("Testing Name")
                .build();

        when(repository.save(any())).thenReturn(expected);

        //Act
        Agenda result = createAgendaService.execute("Testing Name","Testing description","12345678910");

        //Assert
        assertNotNull(result.getId());
        assertEquals(expected.getCreatorDocument(), result.getCreatorDocument());
        assertEquals(expected.getDescription(), result.getDescription());
        assertEquals(expected.getName(), result.getName());
        verify(repository,times(1)).save(any());
    }
}
