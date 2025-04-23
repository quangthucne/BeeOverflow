package com.beeoverflow.apibeeoverflow.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "answer")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id",
//        scope = Answer.class
//)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonManagedReference
    @JsonIgnoreProperties({"answers", "questions"})
    private Account account;

//    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ques_id")
    @JsonIgnoreProperties("answers")
    private Question ques;

    @Lob
    @Column(name = "detail")
    private String detail;

    @ColumnDefault("0")
    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "ans")
    private Set<AnsVote> ansVotes = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonManagedReference
    private Answer parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Answer> answersInParent;

    @OneToMany(mappedBy = "ans")
    @JsonManagedReference
    private List<ImagesAn> imagesAns;

}