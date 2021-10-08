package com.assembly.assembly.service.repository;

import com.assembly.assembly.service.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, UUID> {

    boolean existsBySessionIdAndUserDocument(UUID sessionId, String userDocument);
    List<Vote> findBySessionId(UUID sessionId);

}
