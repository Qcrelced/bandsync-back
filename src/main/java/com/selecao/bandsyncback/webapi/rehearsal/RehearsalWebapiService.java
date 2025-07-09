package com.selecao.bandsyncback.webapi.rehearsal;

import com.selecao.bandsyncback.webapi.dto.RehearsalDto;
import com.selecao.bandsyncback.core.rehearsal.Rehearsal;
import com.selecao.bandsyncback.core.rehearsal.RehearsalService;
import com.selecao.bandsyncback.core.band.Band;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RehearsalWebapiService {

    private final RehearsalService rehearsalService;

    public List<RehearsalDto> getAllRehearsals() {
        return rehearsalService.getAllRehearsals().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public RehearsalDto getRehearsal(Integer id) {
        Rehearsal rehearsal = rehearsalService.getRehearsal(id);
        return convertToDto(rehearsal);
    }

    public List<RehearsalDto> getRehearsalsByBand(Integer bandId) {
        return rehearsalService.getRehearsalsByBand(bandId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<RehearsalDto> getUpcomingRehearsals() {
        return rehearsalService.getUpcomingRehearsals().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<RehearsalDto> getRehearsalsByType(String type) {
        return rehearsalService.getRehearsalsByType(type).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public RehearsalDto createRehearsal(RehearsalDto rehearsalDto) {
        Rehearsal rehearsal = convertToEntity(rehearsalDto);
        Rehearsal savedRehearsal = rehearsalService.createRehearsal(rehearsal);
        return convertToDto(savedRehearsal);
    }

    public RehearsalDto updateRehearsal(Integer id, RehearsalDto rehearsalDto) {
        Rehearsal rehearsal = convertToEntity(rehearsalDto);
        Rehearsal updatedRehearsal = rehearsalService.updateRehearsal(id, rehearsal);
        return convertToDto(updatedRehearsal);
    }

    public void deleteRehearsal(Integer id) {
        rehearsalService.deleteRehearsal(id);
    }

    private RehearsalDto convertToDto(Rehearsal rehearsal) {
        RehearsalDto dto = new RehearsalDto();
        dto.setId(rehearsal.getId());
        dto.setBandId(rehearsal.getBand().getId());
        dto.setScheduledAt(rehearsal.getScheduledAt());
        dto.setLocation(rehearsal.getLocation());
        dto.setDurationMinutes(rehearsal.getDurationMinutes());
        dto.setNotes(rehearsal.getNotes());
        dto.setType(rehearsal.getType());

        // Informations du groupe
        dto.setBandName(rehearsal.getBand().getName());
        dto.setBandGenre(rehearsal.getBand().getMusicalGenre());

        return dto;
    }

    private Rehearsal convertToEntity(RehearsalDto dto) {
        Rehearsal rehearsal = new Rehearsal();
        rehearsal.setId(dto.getId());

        // Créer un objet Band temporaire avec juste l'ID
        Band band = new Band();
        band.setId(dto.getBandId());
        rehearsal.setBand(band);

        rehearsal.setScheduledAt(dto.getScheduledAt());
        rehearsal.setLocation(dto.getLocation());
        rehearsal.setDurationMinutes(dto.getDurationMinutes());
        rehearsal.setNotes(dto.getNotes());
        rehearsal.setType(dto.getType());

        return rehearsal;
    }
}
