package com.beeoverflow.apibeeoverflow.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "question_vote")
public class QuestionVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ques_id")
    private Question ques;

    @ColumnDefault("0")
    @Column(name = "count")
    private Integer count;

}