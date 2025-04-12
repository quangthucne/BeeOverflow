package com.beeoverflow.apibeeoverflow.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Size(max = 255)
    @Column(name = "fullname")
    private String fullname;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 255)
    @Column(name = "avatar")
    private String avatar;

    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;

    @Size(max = 255)
    @Column(name = "password")
    private String password;

    @ColumnDefault("1")
    @Column(name = "isActive")
    private Boolean isActive;

    @Min(0)
    @Max(2)
    @Column(name = "role", length = 50)
    private Integer role;

    @OneToMany(mappedBy = "account")
    private Set<Answer> answers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Document> documents = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Order> orders = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Question> questions;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<Reputation> reputations = new LinkedHashSet<>();

    @NotNull
    @Column(name = "gender", nullable = false)
    private Integer gender;

}