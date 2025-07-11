package com.selecao.bandsyncback.core.band;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class BandService {

    private final BandRepository bandRepository;

    public List<Band> getAllBands() {
        return bandRepository.findAll();
    }

    public Optional<Band> getBandById(Integer id) {
        return bandRepository.findById(id);
    }

    public Band createBand(Band band) {

        return bandRepository.save(band);
    }


    public Band updateBand(Integer id, Band updatedBand) {
        return bandRepository.findById(id).map(existingBand -> {

            existingBand.setName(updatedBand.getName());
            existingBand.setMusicalGenre(updatedBand.getMusicalGenre());
            existingBand.setDescription(updatedBand.getDescription());
            return bandRepository.save(existingBand);
        }).orElseThrow(() -> new RuntimeException("Groupe non trouvé avec l'ID : " + id));
    }

    public void deleteBand(Integer id) {
        if (bandRepository.existsById(id)) {
            bandRepository.deleteById(id);
        } else {
            throw new RuntimeException("Groupe non trouvé avec l'ID : " + id);
        }
    }
}