package com.selecao.bandsyncback.webapi.song;

import com.selecao.bandsyncback.core.song.Song;
import com.selecao.bandsyncback.webapi.dto.SongDto;

public class SongConverter {

    public SongDto songToDto(Song song){
        SongDto songDto=new SongDto();
        songDto.setId(song.getId());
        songDto.setBand(song.getBand());
        songDto.setTitle(song.getTitle());
        songDto.setDuration(song.getDuration());
        return songDto;
    }
}
