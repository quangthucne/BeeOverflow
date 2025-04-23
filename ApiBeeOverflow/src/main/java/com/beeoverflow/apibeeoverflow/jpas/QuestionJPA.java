package com.beeoverflow.apibeeoverflow.jpas;

import com.beeoverflow.apibeeoverflow.dto.QuestionDTO;
import com.beeoverflow.apibeeoverflow.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionJPA extends JpaRepository<Question, Long> {
    Question findById(int id);
}
