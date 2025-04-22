package com.beeoverflow.apibeeoverflow.jpas;

import com.beeoverflow.apibeeoverflow.entities.QuestionVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionVoteJPA extends JpaRepository<QuestionVote, Integer> {
    QuestionVote findById(int id);
}
