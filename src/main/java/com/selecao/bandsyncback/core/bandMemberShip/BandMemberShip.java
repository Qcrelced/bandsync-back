package com.selecao.bandsyncback.core.bandMemberShip;

// Imports corrects pour JPA
import com.selecao.bandsyncback.core.band.Band;
import com.selecao.bandsyncback.core.user.User;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BandMemberShip {

    @Id // Annotation @Id de jakarta.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "band_id", nullable = false)
    private Band band;

}