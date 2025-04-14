package com.beeoverflow.apibeeoverflow.jpas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beeoverflow.apibeeoverflow.entities.Answer;

public interface AnswerJPA extends JpaRepository<Answer, Long>{
    
}
