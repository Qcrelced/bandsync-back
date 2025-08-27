package com.selecao.bandsyncback.core.song;

import com.selecao.bandsyncback.core.band.Band;
import com.selecao.bandsyncback.core.rehearsal.Rehearsal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(mappedBy = "songs")
    private List<Rehearsal> rehearsals = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;
    private String title;
    private String duration;

}
