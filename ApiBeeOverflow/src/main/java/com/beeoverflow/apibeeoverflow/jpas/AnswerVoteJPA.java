package com.beeoverflow.apibeeoverflow.jpas;

import com.beeoverflow.apibeeoverflow.entities.AnsVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerVoteJPA extends JpaRepository<AnsVote, Integer> {
    AnsVote findById(int id);
}
