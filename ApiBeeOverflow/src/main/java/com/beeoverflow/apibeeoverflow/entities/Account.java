package com.beeoverflow.apibeeoverflow.entities;

import com.fasterxml.jackson.annotation.*;
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
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id",
//        scope = Account.class
//)

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

    public enum Role {
        ADMIN(0),
        USER(1),
        USER_HIGH_LV(2);

        private final int value;

        Role(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Role fromValue(int value) {
            for (Role role : Role.values()) {
                if (role.getValue() == value) {
                    return role;
                }
            }
            throw new IllegalArgumentException("Invalid role value: " + value);
        }
    }

    @Min(0)
    @Max(2)
    @Column(name = "role", length = 50)
    private Integer role;

    public Role getRole() {
        return Role.fromValue(this.role);
    }

    @JsonBackReference
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answer> answers;

    @OneToMany(mappedBy = "account")
//    @JsonManagedReference
    private Set<Document> documents = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
//    @JsonManagedReference
    private Set<Order> orders = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Question> questions;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
//    @JsonManagedReference
//    @JsonIgnoreProperties("account")
    private Reputation reputation;

    @NotNull
    @Column(name = "gender", nullable = false)
    private Integer gender;

}