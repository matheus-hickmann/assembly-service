package com.assembly.assembly.service.controller;

import com.assembly.assembly.service.controller.dtos.responses.SessionResponse;
import com.assembly.assembly.service.model.Session;
import com.assembly.assembly.service.service.session.OpenSessionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/agendas/{agendaId}/session")
@Slf4j
public class SessionController {

    private final OpenSessionService openSessionService;
    private final ModelMapper modelMapper;

    @Autowired
    public SessionController(OpenSessionService openSessionService,ModelMapper modelMapper) {
        this.openSessionService = openSessionService;
        this.modelMapper = modelMapper;
    }

    @PatchMapping("/open")
    @Operation(summary = "Open voting session for an agenda")
    public SessionResponse openSession(@PathVariable UUID agendaId, @RequestParam(required = false) Integer duration) {
        log.info("[POST] - /agendas/{}/session-open | Request received:", agendaId.toString());
        Session session = openSessionService.execute(agendaId,duration);
        return modelMapper.map(session, SessionResponse.class);
    }
}
