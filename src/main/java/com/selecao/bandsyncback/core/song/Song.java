package com.selecao.bandsyncback.core.song;

import com.selecao.bandsyncback.core.band.Band;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;
    private String title;
    private String duration;

}
