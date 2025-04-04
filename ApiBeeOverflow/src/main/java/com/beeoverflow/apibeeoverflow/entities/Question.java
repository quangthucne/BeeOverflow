package com.beeoverflow.apibeeoverflow.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Lob
    @Column(name = "detail")
    private String detail;

    @ColumnDefault("0")
    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @ColumnDefault("0")
    @Column(name = "isCheck")
    private Boolean isCheck;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "question")
    private Set<QuestionVote> questionVotes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "question")
    private Set<Tag> tags = new LinkedHashSet<>();

}