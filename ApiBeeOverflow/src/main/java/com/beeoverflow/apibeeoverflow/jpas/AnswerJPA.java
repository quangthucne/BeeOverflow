package com.beeoverflow.apibeeoverflow.jpas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beeoverflow.apibeeoverflow.entities.Answer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AnswerJPA extends JpaRepository<Answer, Integer>{
    Answer findById(int id);

    @Transactional
    @Modifying
    @Query(value = """
    WITH RECURSIVE answer_tree AS (
        SELECT id FROM answer WHERE id = :answerId
        UNION ALL
        SELECT a.id
        FROM answer a
        JOIN answer_tree at ON a.parent_id = at.id
    )
    UPDATE answer
    SET is_deleted = true
    WHERE id IN (SELECT id FROM answer_tree)
    """, nativeQuery = true)
    void softDeleteAnswerTree(@Param("answerId") Integer answerId);

}
