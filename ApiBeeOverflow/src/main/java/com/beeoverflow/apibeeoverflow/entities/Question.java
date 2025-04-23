package com.beeoverflow.apibeeoverflow.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name = "account_id")
    private Account account;

    @Size(max = 255)
    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "detail")
    private String detail;

    @ColumnDefault("0")
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ColumnDefault("0")
    @Column(name = "is_check")
    private Boolean isCheck;

    @JsonManagedReference
    @OneToMany(mappedBy = "ques", fetch = FetchType.LAZY)
    private List<Answer> answers;

    @OneToOne(mappedBy = "ques", fetch = FetchType.LAZY)
    @JsonManagedReference
    private QuestionVote questionVotes;

    @JsonManagedReference
    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Tag> tags;

    @JsonManagedReference
    @OneToMany(mappedBy = "ques", fetch = FetchType.LAZY)
    private List<ImagesQue> imagesQues;


    @Column(name = "created_date")
    private LocalDateTime createdDate;

}