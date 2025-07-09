package com.selecao.bandsyncback.webapi.band;


import com.selecao.bandsyncback.webapi.dto.BandDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/bands")

public class BandController {

    private final BandWebapiService bandWebapiService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<BandDto>> getAllBands() {
        List<BandDto> bandDtos = bandWebapiService.getAllBands();
        return ResponseEntity.ok(bandDtos);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<BandDto> getBandById(@PathVariable Integer id) {
        BandDto bandDto = bandWebapiService.getBand(id);
        return ResponseEntity.ok(bandDto);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<BandDto> createBand(@RequestBody BandDto bandDto) {
        BandDto createdBandDto = bandWebapiService.createBand(bandDto);
        return new ResponseEntity<>(createdBandDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteBand(@PathVariable Integer id) {
        bandWebapiService.deleteBand(id);
        return ResponseEntity.noContent().build();
    }
}
