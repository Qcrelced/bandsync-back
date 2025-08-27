package com.selecao.bandsyncback.webapi.song;

import com.selecao.bandsyncback.core.band.Band;
import com.selecao.bandsyncback.core.rehearsal.RehearsalService;
import com.selecao.bandsyncback.core.song.Song;
import com.selecao.bandsyncback.core.song.SongRepository;
import com.selecao.bandsyncback.core.song.SongService;
import com.selecao.bandsyncback.core.user.User;
import com.selecao.bandsyncback.core.user.UserRepository;
import com.selecao.bandsyncback.webapi.dto.BandDto;
import com.selecao.bandsyncback.webapi.dto.RehearsalDto;
import com.selecao.bandsyncback.webapi.dto.SongDto;
import com.selecao.bandsyncback.webapi.dto.UserDto;
import com.selecao.bandsyncback.webapi.user.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongWebapiService {
    private final SongRepository songRepository;
    private final SongConverter songConvertor;
    private final SongService songService;

    public SongDto getSong(int id) {
        Optional<Song> song = songRepository.findById(id);
        return song.map(songConvertor::songToDto).orElse(null);
    }

    public List<SongDto> getSongByBand(Integer bandId) {
        return songService.getSongByBand(bandId).stream()
                .map(songConvertor::songToDto)
                .collect(Collectors.toList());
    }
    public void deleteSong(Integer id) {
        songService.deleteSong(id);
    }
    public SongDto createSong(SongDto songDto) {
        Song songToCreate = songConvertor.toEntity(songDto);
        Song createdSong = songService.createSong(songToCreate);
        return songConvertor.songToDto(createdSong);
    }
}
