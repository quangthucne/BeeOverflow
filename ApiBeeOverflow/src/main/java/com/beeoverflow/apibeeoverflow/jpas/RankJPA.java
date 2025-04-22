package com.beeoverflow.apibeeoverflow.jpas;

import com.beeoverflow.apibeeoverflow.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankJPA extends JpaRepository<Ranking, Integer> {
    Ranking findById(int id);
}
