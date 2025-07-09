package com.selecao.bandsyncback.webapi.band;


import com.selecao.bandsyncback.core.band.Band;
import com.selecao.bandsyncback.core.band.BandService;
import com.selecao.bandsyncback.webapi.dto.BandDto;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BandWebapiService {

    private final BandService bandService;
    private final BandConverter bandConverter;

    public List<BandDto> getAllBands() {
        List<Band> bands = bandService.getAllBands();
        return bands.stream()
                .map(bandConverter::toDto)
                .collect(Collectors.toList());
    }

    public BandDto getBand(int id) {
        return bandService.getBandById(id)
                .map(bandConverter::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Groupe non trouvé avec l'ID : " + id));
    }

    public BandDto createBand(BandDto bandDto) {
        Band bandToCreate = bandConverter.toEntity(bandDto);
        Band createdBand = bandService.createBand(bandToCreate);
        return bandConverter.toDto(createdBand);
    }

    public void deleteBand(Integer id) {
        try {
            bandService.deleteBand(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Groupe non trouvé avec l'ID : " + id, e);
        }
    }
}