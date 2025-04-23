package com.beeoverflow.apibeeoverflow.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reputation")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id",
//        scope = Reputation.class
//)

public class Reputation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonBackReference
//    @JsonIgnoreProperties({"reputation", "account"})
    private Account account;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_id")
    @JsonManagedReference
    private Ranking rank;

    @Column(name = "point")
    private Integer point;

}