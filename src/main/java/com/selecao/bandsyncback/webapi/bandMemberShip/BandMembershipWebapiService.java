package com.selecao.bandsyncback.webapi.bandMemberShip;

import com.selecao.bandsyncback.core.bandMemberShip.BandMemberShip;
import com.selecao.bandsyncback.core.bandMemberShip.BandMembershipService;
import com.selecao.bandsyncback.webapi.dto.BandMemberShipDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BandMembershipWebapiService {

    private final BandMembershipService bandMembershipService;
    private final BandMembershipConverter bandMembershipConverter;

    public List<BandMemberShipDto> getAllBandMemberships() {
        List<BandMemberShip> memberships = bandMembershipService.getAllBandMemberships();
        return memberships.stream()
                .map(bandMembershipConverter::toDto)
                .collect(Collectors.toList());
    }

    public BandMemberShipDto getBandMembership(int id) {
        return bandMembershipService.getBandMembershipById(id)
                .map(bandMembershipConverter::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Adhésion non trouvée avec l'ID : " + id));
    }

    public BandMemberShipDto createBandMembership(BandMemberShipDto bandMembershipDto) {
        if (bandMembershipDto.getUserId() == null || bandMembershipDto.getBandId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Les IDs utilisateur et groupe sont obligatoires.");
        }
        try {
            BandMemberShip createdMembership = bandMembershipService.createBandMembership(
                    bandMembershipDto.getUserId(),
                    bandMembershipDto.getBandId()
            );
            return bandMembershipConverter.toDto(createdMembership);
        } catch (RuntimeException e) {
            // Traitez les exceptions spécifiques du service métier ici (ex: Utilisateur/Groupe non trouvé, déjà membre)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e); // Ou CONFLICT si déjà membre
        }
    }

    public BandMemberShipDto updateBandMembership(Integer id, BandMemberShipDto bandMembershipDto) {
        if (bandMembershipDto.getUserId() == null || bandMembershipDto.getBandId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Les IDs utilisateur et groupe sont obligatoires.");
        }
        try {
            BandMemberShip updatedMembership = bandMembershipService.updateBandMembership(
                    id,
                    bandMembershipDto.getUserId(),
                    bandMembershipDto.getBandId()
            );
            return bandMembershipConverter.toDto(updatedMembership);
        } catch (RuntimeException e) {
            // Adaptez le statut HTTP en fonction du type d'erreur (404 si non trouvé, 400 si IDs invalides, 409 si conflit)
            if (e.getMessage().contains("non trouvée")) { // Très simple, préférez des exceptions personnalisées
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
            } else if (e.getMessage().contains("existe déjà")) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
            }
        }
    }

    public void deleteBandMembership(Integer id) {
        try {
            bandMembershipService.deleteBandMembership(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Adhésion non trouvée avec l'ID : " + id, e);
        }
    }
}