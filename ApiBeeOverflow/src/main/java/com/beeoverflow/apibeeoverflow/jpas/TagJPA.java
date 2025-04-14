package com.beeoverflow.apibeeoverflow.jpas;

import com.beeoverflow.apibeeoverflow.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagJPA extends JpaRepository<Tag, Integer> {
}
