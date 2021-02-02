package com.uniloftsky.springframework.spring5appliancesrent.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends BaseEntity {

    public User() {}

    public User(String login, String password, String phone, String email, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Renting> rentings = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Item> items = new HashSet<>();

    @Column(unique = true)
    private String login;

    private String password;
    private String phone;
    private String email;
    private String firstName;
    private String lastName;
}
