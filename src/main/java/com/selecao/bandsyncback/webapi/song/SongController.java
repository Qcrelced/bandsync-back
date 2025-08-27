package com.selecao.bandsyncback.webapi.song;

import com.selecao.bandsyncback.core.song.SongService;
import com.selecao.bandsyncback.core.user.UserService;
import com.selecao.bandsyncback.webapi.dto.*;
import com.selecao.bandsyncback.webapi.user.UserWebapiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/song")
public class SongController {
    private final SongWebapiService songWebapiService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<SongDto> createSong(@RequestBody SongDto songDto) {
        SongDto createdSongDto = songWebapiService.createSong(songDto);
        return new ResponseEntity<>(createdSongDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDto> getSongById(@PathVariable int id) {
        SongDto songDto = songWebapiService.getSong(id);
        return ResponseEntity.ok(songDto);
    }

    @GetMapping("/band/{bandId}")
    @ResponseBody
    public ResponseEntity<List<SongDto>> getSongByBand(@PathVariable Integer bandId) {
        List<SongDto> songDtos = songWebapiService.getSongByBand(bandId);
        return ResponseEntity.ok(songDtos);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteSong(@PathVariable Integer id) {
        songWebapiService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }
}
