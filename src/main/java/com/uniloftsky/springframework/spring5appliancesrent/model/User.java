package com.uniloftsky.springframework.spring5appliancesrent.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends BaseEntity {

    public User() {}

    public User(String login, String password, String phone, String email, String firstName, String lastName, LocalDate registerDate) {
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registerDate = registerDate;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Renting> rentings = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Item> items = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @NotBlank(message = "{user.field.NotBlank}")
    @Size(min = 3, max = 15, message = "{user.login.Size}")
    @Column(unique = true)
    private String login;

    private String password;

    @NotBlank(message = "{user.field.NotBlank}")
    @Size(min = 1, max = 15, message = "{user.phone.Size}")
    private String phone;

    @NotBlank(message = "{user.field.NotBlank}")
    private String email;

    @NotBlank(message = "{user.field.NotBlank}")
    private String firstName;

    @NotBlank(message = "{user.field.NotBlank}")
    private String lastName;

    private LocalDate registerDate;
}
