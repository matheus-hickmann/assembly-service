package com.assembly.assembly.service.model;

import com.assembly.assembly.service.enums.SessionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "agenda_session")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {
    @Id
    @Column(name = "id", nullable = false)
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name= "agenda_id")
    private Agenda agenda;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    public SessionStatus getStatus() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(endDate)) {
            return SessionStatus.CLOSED;
        } else if (now.isAfter(startDate) && now.isBefore(endDate)) {
            return SessionStatus.OPENED;
        }

        return SessionStatus.PENDING;
    }

}
