package com.selecao.bandsyncback.webapi.rehearsal;

import com.selecao.bandsyncback.webapi.dto.RehearsalDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RehearsalWebapiService {
    List<RehearsalDto> getAllRehearsals();
    RehearsalDto getRehearsal(Integer id);
    List<RehearsalDto> getRehearsalsByBand(Integer bandId);
    List<RehearsalDto> getUpcomingRehearsals();
    List<RehearsalDto> getRehearsalsByType(String type);
    RehearsalDto createRehearsal(RehearsalDto rehearsalDto);
    RehearsalDto updateRehearsal(Integer id, RehearsalDto rehearsalDto);
    void deleteRehearsal(Integer id);
}

