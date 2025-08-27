package com.selecao.bandsyncback.core.rehearsal;

import com.selecao.bandsyncback.core.band.Band;
import com.selecao.bandsyncback.core.band.BandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RehearsalService {

    private final RehearsalRepository rehearsalRepository;
    private final BandRepository bandRepository;

    public List<Rehearsal> getAllRehearsals() {
        return rehearsalRepository.findAll();
    }

    public Rehearsal getRehearsal(Integer id) {
        return rehearsalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rehearsal not found with id: " + id));
    }

    public List<Rehearsal> getRehearsalsByBand(Integer bandId) {
        return rehearsalRepository.findByBandId(bandId);
    }

    public List<Rehearsal> getUpcomingRehearsals() {
        LocalDateTime now = LocalDateTime.now();
        return rehearsalRepository.findByScheduledAtAfterOrderByScheduledAtAsc(now);
    }

    public List<Rehearsal> getRehearsalsByType(String type) {
        return rehearsalRepository.findByType(type);
    }

    public Rehearsal createRehearsal(Rehearsal rehearsal) {
        Band band = bandRepository.findById(rehearsal.getBand().getId())
                .orElseThrow(() -> new RuntimeException("Band not found with id: " + rehearsal.getBand().getId()));

        rehearsal.setBand(band);
        return rehearsalRepository.save(rehearsal);
    }

    public Rehearsal updateRehearsal(Integer id, Rehearsal rehearsalData) {
        Rehearsal existingRehearsal = getRehearsal(id);

        if (!existingRehearsal.getBand().getId().equals(rehearsalData.getBand().getId())) {
            Band band = bandRepository.findById(rehearsalData.getBand().getId())
                    .orElseThrow(() -> new RuntimeException("Band not found with id: " + rehearsalData.getBand().getId()));
            existingRehearsal.setBand(band);
        }

        existingRehearsal.setScheduledAt(rehearsalData.getScheduledAt());
        existingRehearsal.setLocation(rehearsalData.getLocation());
        existingRehearsal.setDurationMinutes(rehearsalData.getDurationMinutes());
        existingRehearsal.setNotes(rehearsalData.getNotes());
        existingRehearsal.setType(rehearsalData.getType());

        return rehearsalRepository.save(existingRehearsal);
    }

    public void deleteRehearsal(Integer id) {
        if (!rehearsalRepository.existsById(id)) {
            throw new RuntimeException("Rehearsal not found with id: " + id);
        }
        rehearsalRepository.deleteById(id);
    }
}

