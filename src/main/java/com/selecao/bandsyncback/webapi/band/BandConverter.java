package com.selecao.bandsyncback.webapi.band;

import com.selecao.bandsyncback.core.band.Band;

import com.selecao.bandsyncback.webapi.dto.BandDto;

import org.springframework.stereotype.Component;

@Component
public class BandConverter {

    public BandDto toDto(Band band) {
        if (band == null) {
            return null;
        }
        BandDto bandDto = new BandDto();
        bandDto.setId(band.getId());
        bandDto.setName(band.getName());
        bandDto.setDescription(band.getDescription());
        bandDto.setMusicalGenre(band.getMusicalGenre());
        return bandDto;
    }

    public Band toEntity(BandDto bandDto) {
        if (bandDto == null) {
            return null;
        }
        Band band = new Band();

        band.setName(bandDto.getName());
        band.setMusicalGenre(bandDto.getMusicalGenre());
        band.setDescription(bandDto.getDescription());
        return band;
    }


    public Band updateEntityFromDto(Band existingBand, BandDto bandDto) {
        if (existingBand == null || bandDto == null) {
            throw new IllegalArgumentException("Existing band and band DTO cannot be null for update.");
        }

        if (bandDto.getName() != null) {
            existingBand.setName(bandDto.getName());
        }
        if (bandDto.getMusicalGenre() != null) {
            existingBand.setMusicalGenre(bandDto.getMusicalGenre());
        }
        existingBand.setDescription(bandDto.getDescription());

        return existingBand;
    }
}
