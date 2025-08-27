package com.selecao.bandsyncback.webapi.song;

import com.selecao.bandsyncback.core.band.Band;
import com.selecao.bandsyncback.core.song.Song;
import com.selecao.bandsyncback.webapi.dto.BandDto;
import com.selecao.bandsyncback.webapi.dto.SongDto;
import org.springframework.stereotype.Component;

@Component
public class SongConverter {

    public SongDto songToDto(Song song){
        SongDto songDto=new SongDto();
        songDto.setId(song.getId());
        songDto.setBand(song.getBand());
        songDto.setTitle(song.getTitle());
        songDto.setDuration(song.getDuration());
        return songDto;
    }

    public Song toEntity(SongDto songDto) {
        if (songDto == null) {
            return null;
        }
        Song song = new Song();

        song.setTitle(songDto.getTitle());
        song.setDuration(songDto.getDuration());
        song.setBand(songDto.getBand());
        return song;
    }
}
