package com.selecao.bandsyncback.webapi.dto;

import com.selecao.bandsyncback.core.band.Band;
import lombok.Data;

@Data
public class SongDto {

    private int id;
    private Band band;
    private String title;
    private String duration;

}
