package com.beeoverflow.apibeeoverflow.jpas;

import com.beeoverflow.apibeeoverflow.entities.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankJPA extends JpaRepository<Rank, Integer> {
    Rank findById(int id);
}
