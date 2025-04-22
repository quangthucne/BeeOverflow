package com.beeoverflow.apibeeoverflow.jpas;

import com.beeoverflow.apibeeoverflow.entities.Reputation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReputationJPA extends JpaRepository<Reputation, Integer> {
    Reputation findById(int id);
}
