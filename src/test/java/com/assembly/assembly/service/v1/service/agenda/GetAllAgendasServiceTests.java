package com.assembly.assembly.service.v1.service.agenda;

import com.assembly.assembly.service.model.Agenda;
import com.assembly.assembly.service.repository.AgendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllAgendasServiceTests {

    @Mock
    private AgendaRepository repository;

    private GetAllAgendasService getAllAgendasService;

    @BeforeEach
    public void init(){
        getAllAgendasService = new GetAllAgendasService(repository);
    }

    @Test
    public void givenThereAreNoAgenda_shouldReturnAnEmptyList() {
        //Arrange
        List<Agenda> expected = Collections.emptyList();
        when(repository.findAll()).thenReturn(Collections.emptyList());

        //Act
        List<Agenda> result = getAllAgendasService.execute();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void givenThereAreAgendas_shouldReturnAAgendaList() {
        //Arrange
        List<Agenda> expected = Arrays.asList(createAgenda(), createAgenda(), createAgenda(), createAgenda(), createAgenda());
        when(repository.findAll()).thenReturn(expected);

        //Act
        List<Agenda> result = getAllAgendasService.execute();

        //Assert
        assertEquals(expected, result);
    }

    private Agenda createAgenda() {
        UUID id = UUID.randomUUID();

        return Agenda.builder()
                .name(id.toString())
                .id(id)
                .description(id.toString())
                .creatorDocument(id.toString())
                .build();
    }
}
