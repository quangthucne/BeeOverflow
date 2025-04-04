package com.beeoverflow.apibeeoverflow.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "fullname", length = 100)
    private String fullname;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "gender", nullable = false)
    private int gender;

    @ColumnDefault("0")
    @Column(name = "point")
    private Integer point;

    @ColumnDefault("1")
    @Column(name = "isActive")
    private Boolean isActive;

    @OneToMany(mappedBy = "account")
    private Set<Answer> answers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Authentication> authentications = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Document> documents = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Order> orders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Question> questions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Reputation> reputations = new LinkedHashSet<>();

}