package com.selecao.bandsyncback.webapi.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RehearsalDto {
    private Integer id;
    private Integer bandId;
    private LocalDateTime scheduledAt;
    private String location;
    private Integer durationMinutes;
    private String notes;
    private String type;

    private String bandName;
    private String bandGenre;
}

