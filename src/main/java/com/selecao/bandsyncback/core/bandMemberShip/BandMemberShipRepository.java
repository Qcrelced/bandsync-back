package com.selecao.bandsyncback.core.bandMemberShip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BandMemberShipRepository extends JpaRepository<BandMemberShip, Integer> {

    Optional<BandMemberShip> findByUserIdAndBandId(Integer userId, Integer bandId);
    List<BandMemberShip> findByUserId(Integer userId);
    List<BandMemberShip> findByBandId(Integer bandId);
    boolean existsByUserIdAndBandId(Integer userId, Integer bandId);
}