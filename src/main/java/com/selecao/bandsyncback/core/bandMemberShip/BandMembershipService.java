package com.selecao.bandsyncback.core.bandMemberShip;

import com.selecao.bandsyncback.core.band.Band;
import com.selecao.bandsyncback.core.band.BandRepository;
import com.selecao.bandsyncback.core.user.User;
import com.selecao.bandsyncback.core.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BandMembershipService {

    private final BandMemberShipRepository bandMembershipRepository;
    private final UserRepository userRepository; // Pour charger les objets User
    private final BandRepository bandRepository;   // Pour charger les objets Band



    public List<BandMemberShip> getAllBandMemberships() {
        return bandMembershipRepository.findAll();
    }

    public Optional<BandMemberShip> getBandMembershipById(Integer id) {
        return bandMembershipRepository.findById(id);
    }


    public BandMemberShip createBandMembership(Integer userId, Integer bandId) {

        if (bandMembershipRepository.existsByUserIdAndBandId(userId, bandId)) {
            throw new RuntimeException("L'utilisateur est déjà membre de ce groupe.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID : " + userId));
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new RuntimeException("Groupe non trouvé avec l'ID : " + bandId));

        BandMemberShip bandMembership = new BandMemberShip();
        bandMembership.setUser(user);
        bandMembership.setBand(band);

        return bandMembershipRepository.save(bandMembership);
    }


    public BandMemberShip updateBandMembership(Integer id, Integer newUserId, Integer newBandId) {
        return bandMembershipRepository.findById(id).map(existingMembership -> {
            User newUser = userRepository.findById(newUserId)
                    .orElseThrow(() -> new RuntimeException("Nouvel utilisateur non trouvé avec l'ID : " + newUserId));
            Band newBand = bandRepository.findById(newBandId)
                    .orElseThrow(() -> new RuntimeException("Nouveau groupe non trouvé avec l'ID : " + newBandId));


            if (!existingMembership.getUser().getId().equals(newUserId) || !existingMembership.getBand().getId().equals(newBandId)) {
                if (bandMembershipRepository.existsByUserIdAndBandId(newUserId, newBandId)) {
                    throw new RuntimeException("La nouvelle combinaison utilisateur/groupe existe déjà.");
                }
            }

            existingMembership.setUser(newUser);
            existingMembership.setBand(newBand);
            return bandMembershipRepository.save(existingMembership);
        }).orElseThrow(() -> new RuntimeException("Adhésion non trouvée avec l'ID : " + id));
    }

    public void deleteBandMembership(Integer id) {
        if (bandMembershipRepository.existsById(id)) {
            bandMembershipRepository.deleteById(id);
        } else {
            throw new RuntimeException("Adhésion non trouvée avec l'ID : " + id);
        }
    }


}