package com.selecao.bandsyncback.core.rehearsal;

import com.selecao.bandsyncback.core.band.Band;
import com.selecao.bandsyncback.core.song.Song;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "rehearsal_songs",
            joinColumns = @JoinColumn(name = "rehearsal_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> songs = new ArrayList<>();

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

