package com.selecao.bandsyncback.webapi.bandMemberShip;

import com.selecao.bandsyncback.core.bandMemberShip.BandMemberShip;
import com.selecao.bandsyncback.webapi.dto.BandMemberShipDto;
import org.springframework.stereotype.Component;

@Component
public class BandMembershipConverter {

    public BandMemberShipDto toDto(BandMemberShip bandMembership) {
        if (bandMembership == null) {
            return null;
        }
        BandMemberShipDto dto = new BandMemberShipDto();
        dto.setId(bandMembership.getId());
        dto.setUserId(bandMembership.getUser() != null ? bandMembership.getUser().getId() : null);
        dto.setBandId(bandMembership.getBand() != null ? bandMembership.getBand().getId() : null);
        return dto;
    }


    public BandMemberShip toEntity(BandMemberShipDto bandMembershipDto) {
        if (bandMembershipDto == null) {
            return null;
        }
        BandMemberShip bandMembership = new BandMemberShip();

        return bandMembership;
    }


    public BandMemberShip updateEntityFromDto(BandMemberShip existingMembership, BandMemberShipDto bandMembershipDto) {
        if (existingMembership == null || bandMembershipDto == null) {
            throw new IllegalArgumentException("Existing membership and DTO cannot be null for update.");
        }


        return existingMembership;
    }
}