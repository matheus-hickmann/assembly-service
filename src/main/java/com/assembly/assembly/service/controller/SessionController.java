package com.assembly.assembly.service.controller;

import com.assembly.assembly.service.controller.dtos.requests.VoteRequest;
import com.assembly.assembly.service.controller.dtos.responses.SessionResponse;
import com.assembly.assembly.service.controller.dtos.responses.SessionResultResponse;
import com.assembly.assembly.service.controller.dtos.responses.VoteResponse;
import com.assembly.assembly.service.model.Session;
import com.assembly.assembly.service.service.session.OpenSessionService;
import com.assembly.assembly.service.service.vote.ComputeVoteService;
import com.assembly.assembly.service.service.vote.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/agendas/{agendaId}/session")
@Slf4j
public class SessionController {

    private final OpenSessionService openSessionService;
    private final VoteService voteService;
    private final ComputeVoteService computeVoteService;
    private final ModelMapper modelMapper;

    @Autowired
    public SessionController(OpenSessionService openSessionService,VoteService voteService,ComputeVoteService computeVoteService,ModelMapper modelMapper) {
        this.openSessionService = openSessionService;
        this.voteService = voteService;
        this.computeVoteService = computeVoteService;
        this.modelMapper = modelMapper;
    }

    @PatchMapping("/open")
    @Operation(summary = "Open voting session for an agenda")
    public SessionResponse openSession(@PathVariable UUID agendaId, @RequestParam(required = false) Integer duration) {
        log.info("[POST] - /agendas/{}/session/open | Request received:", agendaId.toString());
        Session session = openSessionService.execute(agendaId,duration);
        return modelMapper.map(session, SessionResponse.class);
    }

    @PostMapping("{sessionId}/vote")
    public VoteResponse vote(@PathVariable UUID agendaId, @PathVariable UUID sessionId, @Valid @RequestBody VoteRequest vote) {
        log.info("[POST] - /agendas/{}/session/vote | Request received for user: {}", agendaId.toString(), vote.getDocument());
        return modelMapper.map(voteService.execute(vote.getDocument(),vote.getVote(),sessionId), VoteResponse.class);
    }

    @GetMapping("{sessionId}")
    public SessionResultResponse getById(@PathVariable UUID agendaId,@PathVariable UUID sessionId) {
        log.info("[GET] - /agendas/{}/session/{} | Request received", agendaId.toString(), sessionId);
        return modelMapper.map(computeVoteService.execute(sessionId), SessionResultResponse.class);
    }
}
