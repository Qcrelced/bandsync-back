package com.selecao.bandsyncback.core.song;

import com.selecao.bandsyncback.core.band.Band;
import com.selecao.bandsyncback.core.band.BandRepository;
import com.selecao.bandsyncback.core.rehearsal.Rehearsal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;

    public List<Song> getAllSong() {
        return songRepository.findAll();
    }

    public Optional<Song> getBandById(Integer id) {
        return songRepository.findById(id);
    }

    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    @Transactional(readOnly = true)
    public List<Song> getSongByBand(Integer bandId) {
        return songRepository.findAllByBandId(bandId);
    }

    public Song updateSong(Integer id, Song updatedSong) {
        return songRepository.findById(id).map(existingSong -> {

            existingSong.setTitle(updatedSong.getTitle());
            existingSong.setBand(updatedSong.getBand());
            existingSong.setDuration(updatedSong.getDuration());
            return songRepository.save(existingSong);
        }).orElseThrow(() -> new RuntimeException("Musique non trouvée avec l'ID : " + id));
    }

    public void deleteSong(Integer id) {
        if (songRepository.existsById(id)) {
            songRepository.deleteById(id);
        } else {
            throw new RuntimeException("Musique non trouvée avec l'ID : " + id);
        }
    }
}
