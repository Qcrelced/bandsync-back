package com.selecao.bandsyncback.core.rehearsal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RehearsalRepository extends JpaRepository<Rehearsal, Integer> {

    List<Rehearsal> findByBandId(Integer bandId);

    List<Rehearsal> findByScheduledAtAfterOrderByScheduledAtAsc(LocalDateTime dateTime);

    List<Rehearsal> findByType(String type);
}

