package com.selecao.bandsyncback.core.song;

import com.selecao.bandsyncback.core.band.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song,Integer> {

    Optional<Song> findById(Integer id);

    List<Song> findAllByBandId(Integer songId);

}
