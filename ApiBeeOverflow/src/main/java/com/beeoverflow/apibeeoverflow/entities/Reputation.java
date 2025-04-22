package com.beeoverflow.apibeeoverflow.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reputation")
public class Reputation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_id")
    @JsonManagedReference
    private Ranking rank;

    @Column(name = "point")
    private Integer point;

}