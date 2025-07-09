package com.selecao.bandsyncback.core.rehearsal;

import com.selecao.bandsyncback.core.band.Band;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "rehearsal")
@Data
@EqualsAndHashCode(exclude = "band")
@ToString(exclude = "band")
public class Rehearsal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "band_id", nullable = false)
    private Band band;

    @Column(name = "scheduled_at", nullable = false)
    private LocalDateTime scheduledAt;

    @Column(name = "location")
    private String location;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(name = "notes", length = 512)
    private String notes;

    @Column(name = "type", nullable = false, length = 20)
    private String type;
}

