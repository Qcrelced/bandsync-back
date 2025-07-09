package com.selecao.bandsyncback.webapi.bandMemberShip;

import com.selecao.bandsyncback.webapi.dto.BandMemberShipDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/band-memberships") // Point d'accès de base pour les adhésions
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600) // Configure CORS
public class BandMembershipController {

    private final BandMembershipWebapiService bandMembershipWebapiService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<BandMemberShipDto>> getAllBandMemberships() {
        List<BandMemberShipDto> bandMemberships = bandMembershipWebapiService.getAllBandMemberships();
        return ResponseEntity.ok(bandMemberships);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<BandMemberShipDto> getBandMembershipById(@PathVariable Integer id) {
        BandMemberShipDto bandMembershipDto = bandMembershipWebapiService.getBandMembership(id);
        return ResponseEntity.ok(bandMembershipDto);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<BandMemberShipDto> createBandMembership(@RequestBody BandMemberShipDto bandMembershipDto) {
        BandMemberShipDto createdBandMembershipDto = bandMembershipWebapiService.createBandMembership(bandMembershipDto);
        return new ResponseEntity<>(createdBandMembershipDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<BandMemberShipDto> updateBandMembership(@PathVariable Integer id,  @RequestBody BandMemberShipDto bandMembershipDto) {
        BandMemberShipDto updatedBandMembershipDto = bandMembershipWebapiService.updateBandMembership(id, bandMembershipDto);
        return ResponseEntity.ok(updatedBandMembershipDto);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteBandMembership(@PathVariable Integer id) {
        bandMembershipWebapiService.deleteBandMembership(id);
        return ResponseEntity.noContent().build();
    }
}
