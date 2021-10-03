package com.assembly.assembly.service.controller;

import com.assembly.assembly.service.controller.dtos.requests.AgendaRequest;
import com.assembly.assembly.service.controller.dtos.responses.AgendaResponse;
import com.assembly.assembly.service.model.Agenda;
import com.assembly.assembly.service.service.agenda.CreateAgendaService;
import com.assembly.assembly.service.service.agenda.GetAllAgendasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendas")
@Slf4j
public class AgendaController {

    private final GetAllAgendasService getAllAgendasService;
    private final CreateAgendaService createAgendaService;
    private final ModelMapper modelMapper;

    @Autowired
    public AgendaController(
            GetAllAgendasService getAllAgendasService,
            CreateAgendaService createAgendaService,
            ModelMapper modelMapper) {
        this.getAllAgendasService = getAllAgendasService;
        this.createAgendaService = createAgendaService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @Operation(summary = "Get all agendas")
    public List<AgendaResponse> getAllAgendas() {
        log.info("[GET] - /agendas | Request received");
        List<Agenda> allAgendas = getAllAgendasService.getAllAgendas();
        return allAgendas.stream().map(agenda -> modelMapper.map(agenda,AgendaResponse.class)).toList();
    }

    @PostMapping
    @Operation(summary = "Creates a new agenda")
    public AgendaResponse createAgenda(@RequestBody AgendaRequest request) {
        log.info("[POST] - /agendas | Request received with body: {}", request.toString());
        return modelMapper.map(createAgendaService.createAgenda( request.getName(), request.getDescription(), request.getCreatorDocument()), AgendaResponse.class);
    }

}
