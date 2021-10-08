package com.assembly.assembly.service.v1.controller;

import com.assembly.assembly.service.v1.controller.dtos.requests.AgendaRequest;
import com.assembly.assembly.service.v1.controller.dtos.responses.AgendaResponse;
import com.assembly.assembly.service.model.Agenda;
import com.assembly.assembly.service.v1.service.agenda.CreateAgendaService;
import com.assembly.assembly.service.v1.service.agenda.GetAgendaByIdService;
import com.assembly.assembly.service.v1.service.agenda.GetAllAgendasService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/agendas")
@Slf4j
public class AgendaController {

    private final GetAllAgendasService getAllAgendasService;
    private final GetAgendaByIdService getAgendaByIdService;
    private final CreateAgendaService createAgendaService;
    private final ModelMapper modelMapper;

    @Autowired
    public AgendaController(
            GetAllAgendasService getAllAgendasService,
            GetAgendaByIdService getAgendaByIdService,CreateAgendaService createAgendaService,
            ModelMapper modelMapper) {
        this.getAllAgendasService = getAllAgendasService;
        this.getAgendaByIdService = getAgendaByIdService;
        this.createAgendaService = createAgendaService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @Operation(summary = "Get all agendas")
    public List<AgendaResponse> getAllAgendas() {
        log.info("[GET] - /agendas | Request received");
        List<Agenda> allAgendas = getAllAgendasService.execute();
        return allAgendas.stream().map(agenda -> modelMapper.map(agenda,AgendaResponse.class)).toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an agenda by id")
    public AgendaResponse getAgenda(@PathVariable UUID id) {
        log.info("[GET] - /agendas/{} | Request received", id.toString());
        Agenda agenda = getAgendaByIdService.execute(id);
        return modelMapper.map(agenda,AgendaResponse.class);
    }

    @PostMapping
    @Operation(summary = "Creates a new agenda")
    public AgendaResponse createAgenda(@RequestBody AgendaRequest request) {
        log.info("[POST] - /agendas | Request received with body: {}", request.toString());
        return modelMapper.map(createAgendaService.execute(request.getName(),request.getDescription(),request.getCreatorDocument()),AgendaResponse.class);
    }

}
