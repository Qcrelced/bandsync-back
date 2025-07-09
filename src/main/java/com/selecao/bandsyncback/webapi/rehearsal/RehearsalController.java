package com.selecao.bandsyncback.webapi.rehearsal;

import com.selecao.bandsyncback.webapi.dto.RehearsalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/rehearsals")
public class RehearsalController {

    @Autowired
    private final RehearsalWebapiService rehearsalWebapiService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<RehearsalDto>> getAllRehearsals() {
        List<RehearsalDto> rehearsalDtos = rehearsalWebapiService.getAllRehearsals();
        return ResponseEntity.ok(rehearsalDtos);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RehearsalDto> getRehearsalById(@PathVariable Integer id) {
        RehearsalDto rehearsalDto = rehearsalWebapiService.getRehearsal(id);
        return ResponseEntity.ok(rehearsalDto);
    }

    @GetMapping("/band/{bandId}")
    @ResponseBody
    public ResponseEntity<List<RehearsalDto>> getRehearsalsByBand(@PathVariable Integer bandId) {
        List<RehearsalDto> rehearsalDtos = rehearsalWebapiService.getRehearsalsByBand(bandId);
        return ResponseEntity.ok(rehearsalDtos);
    }

    @GetMapping("/upcoming")
    @ResponseBody
    public ResponseEntity<List<RehearsalDto>> getUpcomingRehearsals() {
        List<RehearsalDto> rehearsalDtos = rehearsalWebapiService.getUpcomingRehearsals();
        return ResponseEntity.ok(rehearsalDtos);
    }

    @GetMapping("/type/{type}")
    @ResponseBody
    public ResponseEntity<List<RehearsalDto>> getRehearsalsByType(@PathVariable String type) {
        List<RehearsalDto> rehearsalDtos = rehearsalWebapiService.getRehearsalsByType(type);
        return ResponseEntity.ok(rehearsalDtos);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<RehearsalDto> createRehearsal(@RequestBody RehearsalDto rehearsalDto) {
        RehearsalDto createdRehearsalDto = rehearsalWebapiService.createRehearsal(rehearsalDto);
        return new ResponseEntity<>(createdRehearsalDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RehearsalDto> updateRehearsal(
            @PathVariable Integer id,
            @RequestBody RehearsalDto rehearsalDto) {
        RehearsalDto updatedRehearsalDto = rehearsalWebapiService.updateRehearsal(id, rehearsalDto);
        return ResponseEntity.ok(updatedRehearsalDto);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteRehearsal(@PathVariable Integer id) {
        rehearsalWebapiService.deleteRehearsal(id);
        return ResponseEntity.noContent().build();
    }
}
