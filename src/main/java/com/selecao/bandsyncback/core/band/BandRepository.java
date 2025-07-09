package com.selecao.bandsyncback.core.band;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BandRepository extends JpaRepository<Band, Integer> {

     Optional<Band> findByName(String name);
     List<Band> findByNameContainingIgnoreCase(String name);
     List<Band> findByMusicalGenre(String musicalGenre);
     List<Band> findByNameContainingIgnoreCaseAndMusicalGenre(String name, String musicalGenre);
}
